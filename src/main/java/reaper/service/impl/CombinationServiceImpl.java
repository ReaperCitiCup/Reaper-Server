package reaper.service.impl;

import Asset_Allocation.Asset_Allocation;
import Asset_Allocation_Factor.Asset_Allocation_Factor;
import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reaper.bean.*;
import reaper.model.*;
import reaper.repository.*;
import reaper.service.CombinationService;
import reaper.service.FundService;
import reaper.service.UserService;
import reaper.util.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static reaper.util.CodeFormatUtil.fillBlank;
import static reaper.util.CodeFormatUtil.getCodeList;

/**
 * @author keenan on 08/09/2017
 */
//TODO 看一下如果未登录会怎么办，需不需要写验证登录的部分

@SuppressWarnings("Duplicates")
@Service
public class CombinationServiceImpl implements CombinationService {
    @Autowired
    private CombinationRepository combinationRepository;
    @Autowired
    private FundNetValueRepository fundNetValueRepository;
    @Autowired
    private BasicStockIndexRepository basicStockIndexRepository;
    @Autowired
    private FundRankRepository fundRankRepository;
    @Autowired
    private FactorResultRepository factorResultRepository;
    @Autowired
    private StockBrinsonResultRepository stockBrinsonResultRepository;
    @Autowired
    private BrisonResultRepository brisonResultRepository;

    @Autowired
    private FundService fundService;
    @Autowired
    private UserService userService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final String FILE_BACK_ANALYSIS = "backtest_analysis.py";
    private static final String FILE_TARGET_PATH = "target_path.py";

    /**
     * 自定创建组合
     * 用户已登录验证，从登录用户中获得用户 code
     * 组合列表不为空，且配比之和为100
     *
     * @param name  组合名
     * @param funds 组合列表
     * @return
     */
    @Override
    public ResultMessage createCombinationByUser(String name, List<FundRatioBean> funds) {
//        User user = userService.getCurrentUser();
//        if (user == null) return ResultMessage.WRONG;
        StringBuilder fundsBuilder = new StringBuilder();
        StringBuilder weightsBuilder = new StringBuilder();

        for (int i = 0; i < funds.size(); i++) {
            FundRatioBean fundRatioBean = funds.get(i);
            String id = (i == funds.size() - 1) ? fundRatioBean.id : fundRatioBean.id + "|";
            String ratio = (i == funds.size() - 1) ? String.valueOf(fundRatioBean.ratio) : fundRatioBean.ratio + "|";
            fundsBuilder.append(id);
            weightsBuilder.append(ratio);
        }

//        Combination combination = new Combination(0, user.getId(), name, fundsBuilder.toString(), weightsBuilder.toString());
        Combination combination = new Combination(0, 123, name, fundsBuilder.toString(), weightsBuilder.toString());

        String[] fundsArray = combination.getFunds().split("\\|");
        String[] weights = combination.getWeights().split("\\|");
        PyAnalysisResult result_withoutRange = getBasicFactors(Arrays.asList(fundsArray));

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year - 1, month, day);
        Date newday = calendar.getTime();
        PyAnalysisResult result_withRange = getBasicFactors(Arrays.asList(fundsArray), simpleDateFormat.format(newday), simpleDateFormat.format(date));

        /**
         * 年化收益率
         */
        try {
            double annualProfit = 0.0;
            for (int i = 0; i < fundsArray.length; i++) {
                String code = fundsArray[i];
                annualProfit += (result_withoutRange.nhsyl.get(code) * Double.valueOf(weights[i]));
            }
            combination.setAnnualProfit(FormatData.fixToTwoAndPercent(annualProfit));
        } catch (Exception e) {
            combination.setAnnualProfit(0.0);
        }


        /**
         * 平均相关系数
         */
        try {
            double sum = 0.0;
            for (PyAnalysisResult.CorrelationCoefficient c : result_withRange.pjxgxs) {
                sum += c.getCc();
            }
            double correlationCoefficient = sum / result_withRange.pjxgxs.size();
            combination.setCorrelationCoefficient(FormatData.fixToTwo(correlationCoefficient));
        } catch (Exception e) {
            combination.setCorrelationCoefficient(0.0);
        }

        /**
         * 最新收益率
         */
        try {
            double newProfit = 0.0;
            for (int i = 0; i < fundsArray.length; i++) {
                newProfit += (fundNetValueRepository.findFirstByCodeOrderByDateDesc(fundsArray[i]).getDailyRate() * Double.valueOf(weights[i]));
            }
            combination.setNewProfit(FormatData.fixToTwoAndPercent(newProfit));
        } catch (Exception e) {
            combination.setNewProfit(0.0);
        }

        try {
            combinationRepository.save(combination);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 我的组合列表
     * 用户已登录验证，从登录用户中获得用户 code
     *
     * @return
     */
    @Override
    public List<CombinationMiniBean> findCombinations() {
        User user = userService.getCurrentUser();
        if (user == null) {
            return Collections.EMPTY_LIST;
        }
        Integer userid = user.getId();
        List<Combination> combinations = combinationRepository.findCombinationsByUserid(userid);
        List<CombinationMiniBean> combinationMiniBeans = new ArrayList<>();
        for (Combination combination : combinations) {
            CombinationMiniBean miniBean = new CombinationMiniBean();
            miniBean.id = combination.getId();
            miniBean.name = combination.getName();
            miniBean.newProfit = combination.getNewProfit();
            miniBean.correlationCoefficient = combination.getCorrelationCoefficient();
            miniBean.annualProfit = combination.getAnnualProfit();

            combinationMiniBeans.add(miniBean);
        }

        return combinationMiniBeans;
    }

    /**
     * 删除组合
     *
     * @param id 组合id
     * @return
     */
    @Override
    public ResultMessage deleteCombination(Integer id) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResultMessage.WRONG;
        }
        Combination combination = combinationRepository.findOne(id);
        if (!combination.getId().equals(user.getId())) {
            return ResultMessage.INVALID;
        }
        try {
            combinationRepository.delete(id);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 回测组合
     *
     * @param combinationId
     * @param startDate
     * @param endDate
     * @param baseIndex     基准指标
     * @return
     */
    @Override
    public BacktestReportBean backtestCombination(Integer combinationId, String startDate, String endDate, String baseIndex) throws java.text.ParseException {
//        if (userService.getCurrentUser() == null) {
//            return null;
//        }

        BacktestReportBean backtestReportBean = new BacktestReportBean();
        int days = DaysBetween.daysOfTwo(simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate));
        List<Date> dateList = DaysBetween.getDatesBetweenTwoDate(simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate));

        /**
         * 基本信息
         */
        backtestReportBean.startDate = startDate;
        backtestReportBean.endDate = endDate;
        backtestReportBean.baseIndex = baseIndex;

        /**
         * 基金组成
         */
        Combination combination = combinationRepository.findOne(combinationId);
        List<String> codes = new ArrayList<>();
        List<Double> weights = new ArrayList<>();
        backtestReportBean.combination = new ArrayList<>();
        for (int i = 0; i < combination.getFunds().split("\\|").length; i++) {
            FundRatioNameBean fundRatioNameBean = new FundRatioNameBean();
            fundRatioNameBean.code = combination.getFunds().split("\\|")[i];
            codes.add(fundRatioNameBean.code);
            fundRatioNameBean.name = fundService.findFundNameByCode(fundRatioNameBean.code).name;
            fundRatioNameBean.weight = FormatData.fixToTwo(Double.parseDouble(combination.getWeights().split("\\|")[i]));
            weights.add(fundRatioNameBean.weight / 100.00);

            backtestReportBean.combination.add(fundRatioNameBean);
        }

        /**
         * 投资目标
         */
        int chosenGoal = combination.getRisk_profit();
        if (chosenGoal >= 1 && chosenGoal <= 3) {
            backtestReportBean.investmentGoal = "低风险低收益";
        } else if (chosenGoal >= 4 && chosenGoal <= 6) {
            backtestReportBean.investmentGoal = "中风险中收益";
        } else if (chosenGoal >= 7 && chosenGoal <= 10) {
            backtestReportBean.investmentGoal = "高风险高收益";
        } else {
            backtestReportBean.investmentGoal = "无";
        }


        /**
         * 组合期末净值、期初净值、夏普比率
         */
        double[] netValues = new double[days];
        double[] dailyRates = new double[days];
        backtestReportBean.sharpeRatio = 0.0;

        for (int i = 0; i < codes.size(); i++) {
            backtestReportBean.sharpeRatio += FormatData.fixToTwo(getBasicFactors(codes, startDate, endDate).sharpe.get(codes.get(i)) * weights.get(i));

            List<FundNetValue> fundNetValues = fundNetValueRepository.findAllByCodeAndDateBetween(codes.get(i), simpleDateFormat.parse(startDate), simpleDateFormat.parse((endDate)));

            for (int j = 0; j < fundNetValues.size(); j++) {
                if (fundNetValues.get(j) != null){
                    netValues[j] += fundNetValues.get(j).getUnitNetValue() * weights.get(i);
                    dailyRates[j] += fundNetValues.get(j).getDailyRate() * weights.get(i);
                }
            }
        }

        double finalNetValue = netValues[codes.size() - 1];
        double startNetValue = netValues[0];

        /**
         * 区间年化收益、累积收益、最终净值
         */
        double fundAnnualProfit = 0.0;
        if (days < 365) {
            fundAnnualProfit = (finalNetValue - startNetValue) / days * 365.0;
        } else {
            int years = days / 365;
            fundAnnualProfit = Math.pow(finalNetValue / startNetValue, 1.0 / years) - 1;
        }

        backtestReportBean.intervalAnnualProfit = FormatData.fixToTwoAndPercent(fundAnnualProfit);
        backtestReportBean.cumulativeProfit = FormatData.fixToTwoAndPercent((finalNetValue - startNetValue) / startNetValue);
        backtestReportBean.finalNetValue = FormatData.fixToTwo(finalNetValue);

        /**
         * 波动率：收益率的标准差
         */
        backtestReportBean.volatility = FormatData.fixToTwo(Calculator.calStandardDeviation(dailyRates));

        /**
         * 主要的三个因子
         */
        backtestReportBean.mainFactors = findMainFactors(codes, weights);

        /**
         * 【图】累计净值
         */
        List<ValueDateBean> baseNetValueList = new ArrayList<>();
        List<ValueDateBean> fundNetValueList = new ArrayList<>();

        List<BasicStockIndex> basicStockIndexList = basicStockIndexRepository.findAllByStockNameAndDateBetweenOrderByDateAsc(baseIndex, simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate));
        for (BasicStockIndex basicStockIndex : basicStockIndexList) {
            ValueDateBean baseValueDateBean = new ValueDateBean(simpleDateFormat.format(basicStockIndex.getDate()), FormatData.fixToTwo(basicStockIndex.getClosePrice()));
            baseNetValueList.add(baseValueDateBean);
        }

        for (int i = 0; i < netValues.length; i++) {
            ValueDateBean fundValueDateBean = new ValueDateBean(simpleDateFormat.format(dateList.get(i)), FormatData.fixToTwo(netValues[i]));
            fundNetValueList.add(fundValueDateBean);
        }

        backtestReportBean.cumulativeNetValueTrend = new BacktestComparisonBean(fundNetValueList, baseNetValueList);

        /**
         * 【图】收益率
         */
        List<ValueDateBean> baseProfitList = new ArrayList<>();
        List<ValueDateBean> fundProfitList = new ArrayList<>();

        for (int i = 0; i < basicStockIndexList.size() - 1; i++) {
            if (basicStockIndexList.get(i).getClosePrice() != 0) {
                ValueDateBean baseValueDateBean = new ValueDateBean(simpleDateFormat.format(basicStockIndexList.get(i + 1).getDate()),
                        FormatData.fixToTwoAndPercent((basicStockIndexList.get(i + 1).getClosePrice() - basicStockIndexList.get(i).getClosePrice()) / basicStockIndexList.get(i).getClosePrice()));
                baseProfitList.add(baseValueDateBean);
            }
        }

        for (int i = 0; i < netValues.length - 1; i++) {
            if (netValues[i] != 0) {
                ValueDateBean fundValueDateBean = new ValueDateBean(simpleDateFormat.format(dateList.get(i + 1)),
                        FormatData.fixToTwoAndPercent((netValues[i + 1] - netValues[i]) / netValues[i]));
                fundProfitList.add(fundValueDateBean);
            }
        }

        backtestReportBean.profitRateTrend = new BacktestComparisonBean(fundProfitList, baseProfitList);

        /**
         * 总收益率 比较
         */
        double finalBaseValue = basicStockIndexList.get(basicStockIndexList.size() - 1).getClosePrice();
        double startBaseValue = basicStockIndexList.get(0).getClosePrice();
        double baseProfit = FormatData.fixToTwoAndPercent((finalBaseValue - startBaseValue) / startBaseValue);
        double fundProfit = FormatData.fixToTwoAndPercent((finalNetValue - startNetValue) / startNetValue);
        backtestReportBean.totalProfitRate = new BacktestValueComparisonBean(fundProfit, baseProfit);

        /**
         * 超额收益率 比较
         */
        backtestReportBean.overProfitRate = new BacktestValueComparisonBean(fundProfit - baseProfit, FormatData.fixToTwoAndPercent(0.0));

        /**
         * 年化收益率 比较
         */
        double baseAnnualProfit = 0.0;
        if (days < 365) {
            baseAnnualProfit = (finalBaseValue - startBaseValue) / days / 100 * 365.0;
        } else {
            int years = days / 365;
            baseAnnualProfit = Math.pow(finalBaseValue / startBaseValue, 1.0 / years) - 1;
        }
        backtestReportBean.annualProfit = new BacktestValueComparisonBean(FormatData.fixToTwoAndPercent(fundAnnualProfit), FormatData.fixToTwoAndPercent(baseAnnualProfit));

        /**
         * 盈利天占比
         */
        double baseProfitDays = 0.0;
        double fundProfitDays = 0.0;

        for (int i = 0; i < basicStockIndexList.size() - 1; i++) {
            if (basicStockIndexList.get(i + 1).getClosePrice() > basicStockIndexList.get(i).getClosePrice()) {
                baseProfitDays++;
            }
        }

        for (int i = 0; i < netValues.length - 1; i++) {
            if (netValues[i + 1] > netValues[i]) {
                fundProfitDays++;
            }
        }

        backtestReportBean.profitDaysRatio = new BacktestValueComparisonBean(FormatData.fixToTwoAndPercent(fundProfitDays / days), FormatData.fixToTwoAndPercent(baseProfitDays / days));

        /**
         * 【图】每日回撤、最大回撤
         */
        List<ValueDateBean> baseRetracementList = new ArrayList<>();
        List<ValueDateBean> fundRetracementList = new ArrayList<>();
        double maxRetracement = 0.0;

        for (int i = basicStockIndexList.size() - 1; i > 1; i--) {
            if (basicStockIndexList.get(i).getClosePrice() != 0) {
                double lastLargerPrice = basicStockIndexList.get(i).getClosePrice();

                for (int j = i - 1; j > 0; j--) {
                    if (basicStockIndexList.get(j).getClosePrice() > basicStockIndexList.get(i).getClosePrice()) {
                        lastLargerPrice = basicStockIndexList.get(j).getClosePrice();
                        break;
                    }
                }
                ValueDateBean baseValueDateBean = new ValueDateBean(simpleDateFormat.format(basicStockIndexList.get(i).getDate()),
                        FormatData.fixToTwoAndPercent((lastLargerPrice - basicStockIndexList.get(i).getClosePrice()) / basicStockIndexList.get(i).getClosePrice()));
                baseRetracementList.add(baseValueDateBean);
            }

        }

        for (int i = netValues.length - 1; i > 1; i--) {
            if (netValues[i] != 0) {
                double lastLargerNetValue = netValues[i];

                for (int j = i - 1; j > 0; j--) {
                    if (netValues[j] > netValues[i]) {
                        lastLargerNetValue = netValues[j];
                        break;
                    }
                }
                double retracement = (lastLargerNetValue - netValues[i]) / netValues[i];
                maxRetracement = (retracement < maxRetracement) ? retracement : maxRetracement;
                ValueDateBean fundValueDateBean = new ValueDateBean(simpleDateFormat.format(dateList.get(i)),
                        FormatData.fixToTwoAndPercent(retracement));
                fundRetracementList.add(fundValueDateBean);
            }
        }
        
        backtestReportBean.dailyRetracementTrend = new BacktestComparisonBean(fundRetracementList, baseRetracementList);
        backtestReportBean.maxRetracement = FormatData.fixToTwoAndPercent(maxRetracement);

        /**
         * 【表】相关系数
         * 平均相关系数
         * beta
         */
        List<BacktestCorrelationTable> backtestCorrelationTables = new ArrayList<>();
        PyAnalysisResult pyAnalysisResult = getBasicFactors(codes, startDate, endDate);
        double sum = 0.0;
        for (PyAnalysisResult.CorrelationCoefficient coefficient : pyAnalysisResult.pjxgxs) {
            sum += coefficient.getCc();
            backtestCorrelationTables.add(new BacktestCorrelationTable(coefficient.getCode1(), coefficient.getCode2(), coefficient.getCc()));
        }
        backtestReportBean.averageCorrelationCoefficient = FormatData.fixToTwo(sum / pyAnalysisResult.pjxgxs.size());
        backtestReportBean.correlationCoefficientTrend = backtestCorrelationTables;

//        double betasum = 0.0;
//        for (int i = 0; i < codes.size(); i++) {
//            betasum += pyAnalysisResult.beta.get(codes.get(i)) * weights.get(i);
//        }
//        backtestReportBean.beta = FormatData.fixToTwo(betasum / codes.size());

        /**
         * 最大单日跌幅、最大连跌天数
         */
        double maxDayDown = 0.0;
        int downDays = 0;
        for (int i = 0; i < netValues.length - 2; i++) {
            maxDayDown = (netValues[i + 1] - netValues[i]) < maxDayDown ? (netValues[i + 1] - netValues[i]) : maxDayDown;
            if (netValues[i + 1] < netValues[i]) {
                downDays++;
            }
        }
        backtestReportBean.maxDayDown = FormatData.fixToTwo(0.0 - maxDayDown);
        backtestReportBean.maxDownDays = downDays;

        /**
         * 年化波动率
         */
        backtestReportBean.annualVolatility = FormatData.fixToTwo(backtestReportBean.volatility * Math.sqrt(252.0));

        /**
         * VaR 在险价值
         */
        backtestReportBean.var = FormatData.fixToTwo(backtestReportBean.volatility * 2.33 * 1.0 / Math.sqrt(52.0));

        /**
         * 风格归因-收益, 风格归因-风险, 行业归因-收益, 行业归因-风险, 品种归因, Brison归因-股票, Brison归因-债券
         */
        // 风格归因-收益
        List<FieldValueBean> styleAttributionProfit = new ArrayList<>();
        // 风格归因-风险
        List<FieldValueBean> styleAttributionRisk = new ArrayList<>();
        // 行业归因-收益
        List<FieldValueBean> industryAttributionProfit = new ArrayList<>();
        // 行业归因-风险
        List<FieldValueBean> industryAttributionRisk = new ArrayList<>();
        // 品种归因
        List<FieldValueBean> varietyAttribution = new ArrayList<>();
        // Brison归因-股票
        List<FieldValueBean> brisonAttributionStock = new ArrayList<>();
        // Brison归因-债券
        List<FieldValueBean> brisonAttributionBond = new ArrayList<>();

        for (int i = 0; i < codes.size(); i++) {
            BrisonResult brisonResult = brisonResultRepository.findByCode(codes.get(i));
            StockBrinsonResult stockBrinsonResult = stockBrinsonResultRepository.findByFundId(codes.get(i));
            FactorResult normal_factorResult = factorResultRepository.findByCodeAndFactorType(codes.get(i), 'N');
            FactorResult risk_factorResult = factorResultRepository.findByCodeAndFactorType(codes.get(i), 'R');


            if (i == 0) {
                // 风格归因-收益
                styleAttributionProfit = ToFieldBean.factorResultToStyleAttribution(normal_factorResult);
                for (int j = 0; j < styleAttributionProfit.size(); j++) {
                    styleAttributionProfit.get(j).value *= weights.get(i);
                }

                // 风格归因-风险
                styleAttributionRisk = ToFieldBean.factorResultToStyleAttribution(risk_factorResult);
                for (int j = 0; j < styleAttributionRisk.size(); j++) {
                    styleAttributionRisk.get(j).value *= weights.get(i);
                }

                // 行业归因-收益
                industryAttributionProfit = ToFieldBean.factorResultToIndustryAttribution(normal_factorResult);
                for (int j = 0; j < industryAttributionProfit.size(); j++) {
                    industryAttributionProfit.get(j).value *= weights.get(i);
                }

                // 行业归因-风险
                industryAttributionRisk = ToFieldBean.factorResultToIndustryAttribution(risk_factorResult);
                for (int j = 0; j < industryAttributionRisk.size(); j++) {
                    industryAttributionRisk.get(j).value *= weights.get(i);
                }

                // 品种归因
                varietyAttribution = ToFieldBean.brisonResultToVarietyAttribution(brisonResult);
                for (int j = 0; j < varietyAttribution.size(); j++) {
                    varietyAttribution.get(j).value *= weights.get(i);
                }

                // Brison归因-股票
                brisonAttributionStock = ToFieldBean.stockBrisonResultToFieldValue(stockBrinsonResult);
                for (int j = 0; j < brisonAttributionStock.size(); j++) {
                    brisonAttributionStock.get(j).value *= weights.get(i);
                }

                // Brison归因-债券
                brisonAttributionBond = ToFieldBean.brisonResultToFieldValue(brisonResult);
                for (int j = 0; j < brisonAttributionBond.size(); j++) {
                    brisonAttributionBond.get(j).value *= weights.get(i);
                }

            } else {
                // 风格归因-收益
                for (int j = 0; j < styleAttributionProfit.size(); j++) {
                    styleAttributionProfit.get(j).value += ToFieldBean.factorResultToStyleAttribution(normal_factorResult).get(j).value * weights.get(i);
                }

                // 风格归因-风险
                for (int j = 0; j < styleAttributionRisk.size(); j++) {
                    styleAttributionRisk.get(j).value += ToFieldBean.factorResultToStyleAttribution(risk_factorResult).get(j).value * weights.get(i);
                }

                // 行业归因-收益
                for (int j = 0; j < industryAttributionProfit.size(); j++) {
                    industryAttributionProfit.get(j).value += ToFieldBean.factorResultToIndustryAttribution(normal_factorResult).get(j).value * weights.get(i);
                }

                // 行业归因-风险
                for (int j = 0; j < industryAttributionRisk.size(); j++) {
                    industryAttributionRisk.get(j).value += ToFieldBean.factorResultToIndustryAttribution(risk_factorResult).get(j).value * weights.get(i);
                }

                // 品种归因
                for (int j = 0; j < varietyAttribution.size(); j++) {
                    varietyAttribution.get(j).value += ToFieldBean.brisonResultToVarietyAttribution(brisonResult).get(j).value * weights.get(i);
                }

                // Brison归因-股票
                for (int j = 0; j < brisonAttributionStock.size(); j++) {
                    brisonAttributionStock.get(j).value += ToFieldBean.stockBrisonResultToFieldValue(stockBrinsonResult).get(j).value * weights.get(i);
                }

                // Brison归因-债券
                for (int j = 0; j < brisonAttributionBond.size(); j++) {
                    brisonAttributionBond.get(j).value += ToFieldBean.brisonResultToFieldValue(brisonResult).get(j).value * weights.get(i);
                }
            }

            if (i == codes.size() - 1) {
                // 风格归因-收益
                for (int j = 0; j < styleAttributionProfit.size(); j++) {
                    styleAttributionProfit.get(j).value = FormatData.fixToTwoAndPercent(styleAttributionProfit.get(j).value);
                }

                // 风格归因-风险
                for (int j = 0; j < styleAttributionRisk.size(); j++) {
                    styleAttributionRisk.get(j).value = FormatData.fixToTwoAndPercent(styleAttributionRisk.get(j).value);
                }

                // 行业归因-收益
                for (int j = 0; j < industryAttributionProfit.size(); j++) {
                    industryAttributionProfit.get(j).value = FormatData.fixToTwoAndPercent(industryAttributionProfit.get(j).value);
                }

                // 行业归因-风险
                for (int j = 0; j < industryAttributionRisk.size(); j++) {
                    industryAttributionRisk.get(j).value = FormatData.fixToTwoAndPercent(industryAttributionRisk.get(j).value);
                }

                // 品种归因
                for (int j = 0; j < varietyAttribution.size(); j++) {
                    varietyAttribution.get(j).value = FormatData.fixToTwoAndPercent(varietyAttribution.get(j).value);
                }

                // Brison归因-股票
                for (int j = 0; j < brisonAttributionStock.size(); j++) {
                    brisonAttributionStock.get(j).value = FormatData.fixToTwoAndPercent(brisonAttributionStock.get(j).value);
                }

                // Brison归因-债券
                for (int j = 0; j < brisonAttributionBond.size(); j++) {
                    brisonAttributionBond.get(j).value = FormatData.fixToTwoAndPercent(brisonAttributionBond.get(j).value);
                }
            }
        }

        backtestReportBean.styleAttributionProfit = styleAttributionProfit;
        backtestReportBean.styleAttributionRisk = styleAttributionRisk;
        backtestReportBean.industryAttributionProfit = industryAttributionProfit;
        backtestReportBean.industryAttributionRisk = industryAttributionRisk;
        backtestReportBean.varietyAttribution = varietyAttribution;
        backtestReportBean.brisonAttributionBond = brisonAttributionBond;
        backtestReportBean.brisonAttributionStock = brisonAttributionStock;

        return backtestReportBean;
    }


    /**
     * 资产配置-选择目标及路径
     *
     * @param targetPath 目标及路径
     * @return
     */
    @Override
    public List<CategoryFundBean> findFundsByTargetAndPath(AssetTargetPathBean targetPath) {
//        if (userService.getCurrentUser() == null) {
//            return Collections.EMPTY_LIST;
//        }

        Integer lamda = targetPath.profitRiskTarget;
        Integer path = targetPath.path;

        List<CategoryFundBean> result = new ArrayList<>();

        /**
         * 资产间分散
         */
        if (path == 1) {
            Integer count = 10;
            for (double i = 1.0; i <= 3.0; i += 1.0) {
                List<String> tmp = getTargetPathCodes(lamda, count, path, (int) i, 0);
                String cname = FactorNumberMapping.no2FundType(i);
                List<MiniBean> miniBeans = new ArrayList<>();
                for (String code : tmp) {
                    miniBeans.add(fundService.findFundNameByCode(code));
                }
                result.add(new CategoryFundBean(cname, miniBeans));
            }
            return result;
        }
        /**
         * 因子间分散
         */
        else if (path == 2) {
            Integer count = 3;
            for (String s : targetPath.factor) {
                List<String> tmp = getTargetPathCodes(lamda, count, path, 0, FactorNumberMapping.factorName2No(s).intValue());
                String fname = s;
                List<MiniBean> miniBeans = new ArrayList<>();
                for (String code : tmp) {
                    miniBeans.add(fundService.findFundNameByCode(code));
                }
                result.add(new CategoryFundBean(fname, miniBeans));
            }
            return result;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 资产配置-选择基金生成组合
     *
     * @param fundCombination 组合
     * @return
     */
    @Override
    //TODO 测试
    public ResultMessage createCombinationByAssetAllocation(FundCombinationBean fundCombination) {
//        User user = userService.getCurrentUser();
//        if (user == null) {
//            return ResultMessage.WRONG;
//        }

        /**
         * 静态比例配置要特别处理
         */
        if (fundCombination.method == 1) {
            List<String> funds = new ArrayList<>();
            for (FundCategoryBean categoryBean : fundCombination.funds) {
                funds.addAll(categoryBean.codes);
            }

            int size = funds.size();
            double ration = 1.0 / size;

            List<FundRatioBean> fundRatioBeans = new ArrayList<>();
            for (String s : funds) {
                fundRatioBeans.add(new FundRatioBean(s, ration));
            }

            return createCombinationByUser(fundCombination.name, fundRatioBeans);
        }

        Map<String, Double> result = new HashMap<>();
        int uncentralize_type = fundCombination.path;
        int portfolioType = fundCombination.method;
        List<String> codes = new ArrayList<>();
        List<Double> input_kind = new ArrayList<>();
        List<Double> input_weight = new ArrayList<>();

        /**
         * 资产间分散
         */
        if (uncentralize_type == 1) {
            AssetWeightBean weightBean = fundCombination.weight;
            input_weight.add(weightBean.bond / 100.00);
            input_weight.add(weightBean.stock / 100.00);
            input_weight.add(weightBean.hybrid / 100.00);

            for (FundCategoryBean categoryBean : fundCombination.funds) {
                Double category = FactorNumberMapping.fundType2No(categoryBean.category);
                for (String code : categoryBean.codes) {
                    codes.add(code);
                    input_kind.add(category);
                }
            }

            result = calComponentWeight(codes, portfolioType, input_kind, input_weight, uncentralize_type, fundCombination.profitRate / 100.00);
        }
        /**
         * 因子间分散
         */
        else if (uncentralize_type == 2) {
            for (FundCategoryBean categoryBean : fundCombination.funds) {
                Double category = FactorNumberMapping.factorName2No(categoryBean.category);
                for (String code : categoryBean.codes) {
                    codes.add(code);
                    input_kind.add(category);
                }
            }

            result = calComponentWeight(codes, portfolioType, input_kind, null, uncentralize_type, fundCombination.profitRate / 100.00);
        } else return ResultMessage.INVALID;

        if (result == null || result.isEmpty()) {
            return ResultMessage.FAILED;
        }
        List<FundRatioBean> beans = new ArrayList<>();
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            beans.add(new FundRatioBean(entry.getKey(), entry.getValue()));
        }

        for (FundRatioBean bean : beans) {
            System.out.println("&\t" + bean.id + " " + bean.ratio);
        }

        return createCombinationByUser(fundCombination.name, beans);
    }

    /**
     * 创建组合时获得各基金的权重 matlab调用
     *
     * @param codes         基金代码
     * @param portfolioType 分散化方法类型
     * @return <基金代码, 权重(未百分化的double)>
     */
    private Map<String, Double> calComponentWeight(List<String> codes, int portfolioType, List<Double> input_kind, List<Double> input_weight, int uncentralize_type, double profitRate) {
        Map<String, Double> resultMap = new HashMap<>();
        List<String> sorted = new ArrayList<>(codes);
        Collections.sort(sorted);
        String[] strings = codes.toArray(new String[codes.size()]);
        Double[] input_kind_array = input_kind.toArray(new Double[input_kind.size()]);
        Double[] input_weight_array = input_weight.toArray(new Double[input_weight.size()]);

        /**
         * 资产间分散需要的参数
         *
         *  String[] codes = {"000005","150039","002853","519746","150197","000007"};
         *  double[] input_kind={1,1,1,2,2,2};//输入资产的种类，1代表债券型基金，2代表股票型基金，3代表混合型
         *  double[] input_weight={0.7,0.3,0};//大类资产配置的比例，债券型0.7，股票型0.3，混合型0

         *  输入input_kind的长度必须和codes对应，input_weight(m3)指
         *  大类资产比例（0.7指所有债券基金配置比例和占70%，0.3指股票型基金配置比例和占30%）
         *  这个参数应该是前面选择时保留下来的，如果配置比例是0.6,0.4,0，就相应调整。
         *  另外，codes,input_kind与input_weight是互相对应，上面的初始化表示"000005","150039","002853"
         *  这三支基金是债券型基金，其配置的比例和为0.7，"519746","150197","000007"是股票型基金，其
         *  配置的比例之和为0.3。
         */
        if (uncentralize_type == 1) {
            Object[] result = null;
            Asset_Allocation assetAllocation = null;
            MWCharArray funds = null;
            MWNumericArray pType = null;
            MWNumericArray inputKind = null;
            MWNumericArray inputWeight = null;

            try {
                funds = new MWCharArray(strings);
                pType = new MWNumericArray(portfolioType);
                inputKind = new MWNumericArray(input_kind_array);
                inputWeight = new MWNumericArray(input_weight_array);
                assetAllocation = new Asset_Allocation();
                if (portfolioType == 2) {
                    result = assetAllocation.asset_arrangement(1, funds, pType, inputKind, inputWeight, new MWNumericArray(profitRate));
                } else {
                    result = assetAllocation.asset_arrangement(1, funds, pType, inputKind, inputWeight);
                }
                String[] res = null;
                if (result != null && result.length != 0) {
                    res = result[0].toString().replaceAll("[ ]+", " ").split(" ");
                } else {
                    return Collections.EMPTY_MAP;
                }

                if (res == null || res.length != codes.size()) {
                    return Collections.EMPTY_MAP;
                }

                for (int i = 0; i < sorted.size(); i++) {
                    resultMap.put(sorted.get(i), Double.valueOf(res[i]));
                }

                return resultMap;
            } catch (MWException e) {
                return Collections.EMPTY_MAP;
            } finally {
                MWArray.disposeArray(result);
                MWArray.disposeArray(funds);
                MWArray.disposeArray(pType);
                MWArray.disposeArray(inputKind);
                MWArray.disposeArray(inputWeight);

                if (assetAllocation != null) {
                    assetAllocation.dispose();
                    assetAllocation = null;
                }
            }
            /**
             * 因子间分散需要的参数
             *
             *  String[] codes = {"000007","000004","000017","000024","000025","000026","000052","000027","000065","000050","000003","000039","000042"};
             *  double[] input_kind={1,1,1,2,2,8,8,5,5,6,4,4,4};//输入因子的种类
             *  输入input_kind的长度必须和codes对应，input_facotr_num是指因子个数，input_kind指因子种类，其他
             *  变化不大，对参数格式有疑问可以问李振安
             */
        } else if (uncentralize_type == 2) {
            Object[] result = null;
            Asset_Allocation_Factor asset_allocation_factor = null;
            MWCharArray funds = null;
            MWNumericArray pType = null;
            MWNumericArray inputKind = null;
            MWNumericArray inputFactorNum = null;
            int input_factor_num = new HashSet<>(input_kind).size();

            try {
                asset_allocation_factor = new Asset_Allocation_Factor();
                funds = new MWCharArray(strings);
                pType = new MWNumericArray(portfolioType);
                inputKind = new MWNumericArray(input_kind_array);
                inputFactorNum = new MWNumericArray(input_factor_num);

                if (portfolioType == 2) {
                    result = asset_allocation_factor.factor_arrangement(1, funds, pType, inputKind, inputFactorNum, new MWNumericArray(profitRate));
                } else {
                    result = asset_allocation_factor.factor_arrangement(1, funds, pType, inputKind, inputFactorNum);
                }
                String[] res = null;
                if (result != null && result.length != 0) {
                    res = result[0].toString().replaceAll("[ ]+", " ").split(" ");
                } else {
                    return Collections.EMPTY_MAP;
                }

                if (res == null || res.length != codes.size()) {
                    return Collections.EMPTY_MAP;
                }

                for (int i = 0; i < sorted.size(); i++) {
                    resultMap.put(sorted.get(i), Double.valueOf(res[i]));
                }

                return resultMap;
            } catch (MWException e) {
                return Collections.EMPTY_MAP;
            } finally {
                MWArray.disposeArray(result);
                MWArray.disposeArray(funds);
                MWArray.disposeArray(pType);
                MWArray.disposeArray(inputKind);
                MWArray.disposeArray(inputFactorNum);

                if (asset_allocation_factor != null) {
                    asset_allocation_factor.dispose();
                    asset_allocation_factor = null;
                }
            }
        } else {
            return Collections.EMPTY_MAP;
        }

    }

    /**
     * 调用python代码 backtest_analysis.py
     * 可得到年化收益率，年化波动率，在险价值，收益率序列的下行标准差，夏普比率，beta，特雷诺指数，择股系数，择时系数，相关系数
     *
     * @param codes     代码
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    private PyAnalysisResult getBasicFactors(List<String> codes, String startDate, String endDate) {
        PyAnalysisResult result = new PyAnalysisResult();
        String pyRes = PythonUser.usePy(FILE_BACK_ANALYSIS, "1" + " " + startDate + " " + endDate + " " + fillBlank(codes));
        String[] lines = pyRes.split("\n");
        List<String> useful = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("#")) {
                useful.add(line);
            }
        }
        List<PyAnalysisResult.CorrelationCoefficient> coefficients = new ArrayList<>();
        for (String each : useful) {
            String[] values = each.split(" ");
            if (values[2].equals("年化收益率=")) {
                result.nhsyl.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("年化波动率=")) {
                result.nhbdl.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("在险价值=")) {
                result.zxjz.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("收益率序列的下行标准差=")) {
                result.xxbzc.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("夏普比=")) {
                result.sharpe.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("beta=")) {
                result.beta.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("特雷诺指数=")) {
                result.tln.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("择股系数=")) {
                result.zgxs.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("择时系数=")) {
                result.zsxs.put(values[1], Double.valueOf(values[3]));
            } else {
                PyAnalysisResult.CorrelationCoefficient correlationCoefficient = result.new CorrelationCoefficient(values[1], values[2], Double.valueOf(values[3]));
                coefficients.add(correlationCoefficient);
            }
        }
        result.setPjxgxs(coefficients);
        return result;
    }

    /**
     * 调用python代码 backtest_analysis.py
     * 可得到年化收益率，年化波动率，在险价值，收益率序列的下行标准差，夏普比率，beta，特雷诺指数，择股系数，择时系数
     *
     * @param codes 代码
     * @return
     */
    private PyAnalysisResult getBasicFactors(List<String> codes) {
        PyAnalysisResult result = new PyAnalysisResult();
        String pyRes = PythonUser.usePy(FILE_BACK_ANALYSIS, "0" + " " + fillBlank(codes));
        String[] lines = pyRes.split("\n");
        List<String> useful = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("*")) {
                useful.add(line);
            }
        }
        for (String each : useful) {
            String[] values = each.split(" ");
            if (values[2].equals("年化收益率=")) {
                result.nhsyl.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("年化波动率=")) {
                result.nhbdl.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("在险价值=")) {
                result.zxjz.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("收益率序列的下行标准差=")) {
                result.xxbzc.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("夏普比=")) {
                result.sharpe.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("beta=")) {
                result.beta.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("特雷诺指数=")) {
                result.tln.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("择股系数=")) {
                result.zgxs.put(values[1], Double.valueOf(values[3]));
            } else if (values[2].equals("择时系数=")) {
                result.zsxs.put(values[1], Double.valueOf(values[3]));
            }
        }
        return result;
    }


    /**
     * 根据参数调取python获得基金代码
     *
     * @param lamda       是彩虹条的那个选择
     * @param count       资产间分散为10，因子间分散为3
     * @param sqlkind     1为资产减分散，2为因子间分散
     * @param type_kind   只在资产间分散的时候要用，1债券型，2股票型，3混合型 要分三次调用
     * @param factor_kind 只在因子间分散时要用 要分10次调用
     * @return
     */
    private List<String> getTargetPathCodes(Integer lamda, Integer count, Integer sqlkind, Integer type_kind, Integer factor_kind) {
        lamda = (lamda == null) ? 5 : lamda;
        count = (count == null) ? 8 : count;
        sqlkind = (sqlkind == null) ? 1 : sqlkind;
        type_kind = (type_kind == null) ? 1 : type_kind;
        factor_kind = (factor_kind == null) ? 1 : factor_kind;

        String pyRes = PythonUser.usePy(FILE_TARGET_PATH, lamda + " " + count + " " + sqlkind + " " + type_kind + " " + factor_kind);

        if (pyRes.equals("") || pyRes == null || pyRes.equals("[]") || pyRes.equals("[ ]")) {
            return Collections.EMPTY_LIST;
        } else {
            return getCodeList(pyRes);
        }
    }

    /**
     * 获得主要的三个因子
     *
     * @param codes
     * @param weights 权重（使用时需要除以100）
     * @return
     */
    private List<String> findMainFactors(List<String> codes, List<Double> weights) {
        Map<String, Double> factor_sum = new HashMap<>();
        /**
         * 1 beta beta
         * 2 btop 价值
         * 3 earningsyield 盈利能力 profit
         * 4 growth 成长性
         * 5 leverage 杠杆率
         * 6 liquidity 流动性
         * 7 momentum 动量
         * 8 nlsize 非线性市值
         * 9 residualvolatility 波动率
         * 10 size 市值
         */
        factor_sum.put("beta", 0.0);
        factor_sum.put("btop", 0.0);
        factor_sum.put("profit", 0.0);
        factor_sum.put("growth", 0.0);
        factor_sum.put("leverage", 0.0);
        factor_sum.put("liquidity", 0.0);
        factor_sum.put("momentum", 0.0);
        factor_sum.put("nlsize", 0.0);
        factor_sum.put("residualvolatility", 0.0);
        factor_sum.put("size", 0.0);
        for (int i = 0; i < codes.size(); i++) {
            String code = codes.get(i);
            double weight = weights.get(i);
            FundRank fundRank = fundRankRepository.findOne(code);
            if (fundRank == null) {
                continue;
            }
            Double beta = factor_sum.get("beta");
            beta += fundRank.getRank1() * weight;
            factor_sum.put("beta", beta);

            Double btop = factor_sum.get("btop");
            btop += fundRank.getRank2() * weight;
            factor_sum.put("btop", btop);

            Double profit = factor_sum.get("profit");
            profit += fundRank.getRank3() * weight;
            factor_sum.put("profit", profit);

            Double growth = factor_sum.get("growth");
            growth += fundRank.getRank4() * weight;
            factor_sum.put("growth", growth);

            Double leverage = factor_sum.get("leverage");
            leverage += fundRank.getRank5() * weight;
            factor_sum.put("leverage", leverage);

            Double liquidity = factor_sum.get("liquidity");
            liquidity += fundRank.getRank6() * weight;
            factor_sum.put("liquidity", liquidity);

            Double momentum = factor_sum.get("momentum");
            momentum += fundRank.getRank7() * weight;
            factor_sum.put("momentum", momentum);

            Double nlsize = factor_sum.get("nlsize");
            nlsize += fundRank.getRank8() * weight;
            factor_sum.put("nlsize", nlsize);

            Double residualvolatility = factor_sum.get("residualvolatility");
            residualvolatility += fundRank.getRank9() * weight;
            factor_sum.put("residualvolatility", residualvolatility);

            Double size = factor_sum.get("size");
            size += fundRank.getRank10() * weight;
            factor_sum.put("size", size);
        }

        List<Map.Entry<String, Double>> entries = new ArrayList<>(factor_sum.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return (int) ((o2.getValue() - o1.getValue()) * 10000);
            }
        });

        List<String> res = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            res.add(entries.get(i).getKey());
        }

        return res;
    }
}