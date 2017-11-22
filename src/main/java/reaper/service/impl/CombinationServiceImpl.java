package reaper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reaper.bean.*;
import reaper.model.*;
import reaper.repository.*;
import reaper.service.CombinationService;
import reaper.service.FundService;
import reaper.service.UserService;
import reaper.util.*;
import reaper.util.backtest_util.PortfolioMatlabResultGetter;
import reaper.util.backtest_util.PyAnalysisResult;

import java.text.SimpleDateFormat;
import java.util.*;

import static reaper.util.backtest_util.BackTestPyAnalysisGetter.getBasicFactors;
import static reaper.util.backtest_util.PortfolioMatlabResultGetter.calComponentWeight;
import static reaper.util.backtest_util.PortfolioTargetPathPythonGetter.getTargetPathCodes;

/**
 * @author keenan on 08/09/2017
 */
@SuppressWarnings("Duplicates")
@Service
public class CombinationServiceImpl implements CombinationService {
    @Autowired
    private CombinationRepository combinationRepository;
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
    private CombinationAnalysisRepository combinationAnalysisRepository;
    @Autowired
    private FundService fundService;
    @Autowired
    private UserService userService;
    @Autowired
    private RankDataRfRepository rankDataRfRepository;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


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
    public ResultMessage createCombinationByUser(String name, List<FundRatioBean> funds, Integer profitRisk, CombinationAnalysis combinationAnalysis) {
        User user = userService.getCurrentUser();
        if (user == null) return ResultMessage.WRONG;
        StringBuilder fundsBuilder = new StringBuilder();
        StringBuilder weightsBuilder = new StringBuilder();

        for (Combination existCombination : combinationRepository.findCombinationsByUserid(user.getId())) {
            if (existCombination.getName().equals(name)) {
                return ResultMessage.EXIST;
            }
        }

        for (int i = 0; i < funds.size(); i++) {
            FundRatioBean fundRatioBean = funds.get(i);
            String id = (i == funds.size() - 1) ? fundRatioBean.id : fundRatioBean.id + "|";
            String ratio = (i == funds.size() - 1) ? String.valueOf(fundRatioBean.ratio) : fundRatioBean.ratio + "|";
            fundsBuilder.append(id);
            weightsBuilder.append(ratio);
        }

        Combination combination = new Combination(profitRisk, user.getId(), name, fundsBuilder.toString(), weightsBuilder.toString());

        String[] fundsArray = combination.getFunds().split("\\|");
        String[] weights = combination.getWeights().split("\\|");
        List<Double> percentage = new ArrayList<>();
        for (String weight : weights) {
            percentage.add(Double.valueOf(weight));
        }
        PyAnalysisResult result = getBasicFactors(Arrays.asList(fundsArray), percentage, "1900-05-05", simpleDateFormat.format(new Date()));

        /**
         * 年化收益率
         */
        try {
            double annualProfit = result.getNhsyl();
            System.out.println("annualProfit " + annualProfit);
            combination.setAnnualProfit(FormatData.fixToTwoAndPercent(annualProfit));
        } catch (Exception e) {
            e.printStackTrace();
            combination.setAnnualProfit(0.0);
        }


        /**
         * 年化波动率
         */
        try {
            double volatility = result.getNhbdl();
            System.out.println("volatility: " + volatility);
            combination.setVolatility(FormatData.fixToTwoAndPercent(volatility));
        } catch (Exception e) {
            e.printStackTrace();
            combination.setVolatility(0.0);
        }

        /**
         * 最新收益率
         */
        try {
            List<ValueDateBean> returnRates = result.getDailyReturnRate();
            double newProfit = returnRates.get(returnRates.size() - 1).value;
            combination.setNewProfit(FormatData.fixToTwoAndPercent(newProfit));
        } catch (Exception e) {
            e.printStackTrace();
            combination.setNewProfit(0.0);
        }

        try {
            int combinationID = combinationRepository.save(combination).getId();
            if (combinationAnalysis != null) {
                combinationAnalysis.setId(combinationID);
                combinationAnalysisRepository.save(combinationAnalysis);
            }
            return ResultMessage.SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
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
            miniBean.volatility = combination.getVolatility();
            miniBean.annualProfit = combination.getAnnualProfit();
            miniBean.hasRisk = combination.getHasRisk();
            miniBean.combination = new ArrayList<>();

            for (int i = 0; i < combination.getFunds().split("\\|").length; i++) {
                FundRatioNameBean fundRatioNameBean = new FundRatioNameBean();
                fundRatioNameBean.code = combination.getFunds().split("\\|")[i];
                fundRatioNameBean.name = fundService.findFundNameByCode(fundRatioNameBean.code).name;
                fundRatioNameBean.weight = FormatData.fixToTwo(Double.parseDouble(combination.getWeights().split("\\|")[i]));
                miniBean.combination.add(fundRatioNameBean);
            }

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
        if (!combination.getUserid().equals(user.getId())) {
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
        if (userService.getCurrentUser() == null) {
            return null;
        }

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
            weights.add(fundRatioNameBean.weight / 100);

            backtestReportBean.combination.add(fundRatioNameBean);
        }

        PyAnalysisResult pyAnalysisResult = getBasicFactors(codes, weights, startDate, endDate);

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
        List<ValueDateBean> netValues = pyAnalysisResult.getCumNet();
        List<ValueDateBean> dailyRates = pyAnalysisResult.getDailyReturnRate();
        backtestReportBean.sharpeRatio = pyAnalysisResult.getSharpe();


        double finalNetValue = pyAnalysisResult.getQmjz();
        double startNetValue = pyAnalysisResult.getQcjz();

        /**
         * 区间年化收益、累积收益、最终净值
         */
        double fundAnnualProfit = pyAnalysisResult.getNhsyl();

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

        List<BasicStockIndex> basicStockIndexList = basicStockIndexRepository.findAllByStockNameAndDateBetweenOrderByDateAsc(baseIndex, simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate));
        for (BasicStockIndex basicStockIndex : basicStockIndexList) {
            ValueDateBean baseValueDateBean = new ValueDateBean(simpleDateFormat.format(basicStockIndex.getDate()), FormatData.fixToTwo(basicStockIndex.getClosePrice()));
            baseNetValueList.add(baseValueDateBean);
        }


        backtestReportBean.cumulativeNetValueTrend = new BacktestComparisonBean(netValues, baseNetValueList);

        /**
         * 【图】收益率
         */
        List<ValueDateBean> baseProfitList = new ArrayList<>();

        for (int i = 0; i < basicStockIndexList.size() - 1; i++) {
            if (basicStockIndexList.get(i).getClosePrice() != 0) {
                ValueDateBean baseValueDateBean = new ValueDateBean(simpleDateFormat.format(basicStockIndexList.get(i + 1).getDate()),
                        FormatData.fixToTwoAndPercent((basicStockIndexList.get(i + 1).getClosePrice() - basicStockIndexList.get(i).getClosePrice()) / basicStockIndexList.get(i).getClosePrice()));
                baseProfitList.add(baseValueDateBean);
            }
        }

        backtestReportBean.profitRateTrend = new BacktestComparisonBean(dailyRates, baseProfitList);

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

        for (int i = 0; i < netValues.size() - 1; i++) {
            if (netValues.get(i + 1).value > netValues.get(i).value) {
                fundProfitDays++;
            }
        }

        backtestReportBean.profitDaysRatio = new BacktestValueComparisonBean(FormatData.fixToTwoAndPercent(fundProfitDays / days), FormatData.fixToTwoAndPercent(baseProfitDays / days));

        /**
         * 【图】每日回撤、最大回撤
         */
        List<ValueDateBean> baseRetracementList = new ArrayList<>();
        List<ValueDateBean> fundRetracementList = pyAnalysisResult.getDailyRetrace();
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

        for (ValueDateBean eachBean : fundRetracementList) {
            maxRetracement = (maxRetracement < eachBean.value) ? eachBean.value : maxRetracement;
        }


        // 前端不展示倒排日期的list
        Collections.reverse(baseRetracementList);

        backtestReportBean.dailyRetracementTrend = new BacktestComparisonBean(fundRetracementList, baseRetracementList);
        backtestReportBean.maxRetracement = FormatData.fixToTwoAndPercent(maxRetracement);

        /**
         * 【表】相关系数
         * 平均相关系数
         * beta
         */
        List<BacktestCorrelationTable> backtestCorrelationTables = new ArrayList<>();
        double sum = 0.0;
        for (PyAnalysisResult.CorrelationCoefficient coefficient : pyAnalysisResult.getPjxgxs()) {
            sum += coefficient.getCc();
            backtestCorrelationTables.add(new BacktestCorrelationTable(coefficient.getCode1(), coefficient.getCode2(), coefficient.getCc()));
        }
        System.out.println(sum);
        System.out.println(pyAnalysisResult.getPjxgxs().size());
        if (pyAnalysisResult.getPjxgxs().size() == 0) {
            backtestReportBean.averageCorrelationCoefficient = 0.0;
        } else {
            backtestReportBean.averageCorrelationCoefficient = FormatData.fixToTwo(sum / pyAnalysisResult.getPjxgxs().size());
        }

        backtestReportBean.correlationCoefficientTable = backtestCorrelationTables;

        /**
         * 最大单日跌幅、最大连跌天数
         */
        double maxDayDown = 0.0;
        int downDays = 0;
        for (int i = 0; i < netValues.size() - 2; i++) {
            maxDayDown = (netValues.get(i + 1).value - netValues.get(i).value) < maxDayDown ? (netValues.get(i + 1).value - netValues.get(i).value) : maxDayDown;
            if (netValues.get(i + 1).value < netValues.get(i).value) {
                downDays++;
            }
        }
        backtestReportBean.maxDayDown = FormatData.fixToTwo(0.0 - maxDayDown);
        backtestReportBean.maxDownDays = downDays;

        /**
         * 年化波动率
         */
        backtestReportBean.annualVolatility = FormatData.fixToTwo(pyAnalysisResult.getNhbdl());

        /**
         * VaR 在险价值
         */
        backtestReportBean.var = FormatData.fixToTwo(pyAnalysisResult.getZxjz());

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

            if (brisonResult == null || stockBrinsonResult == null || normal_factorResult == null || risk_factorResult == null) {
                styleAttributionProfit.clear();
                styleAttributionProfit.clear();
                industryAttributionProfit.clear();
                industryAttributionRisk.clear();
                varietyAttribution.clear();
                brisonAttributionBond.clear();
                brisonAttributionStock.clear();
                break;
            }

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
        }

        backtestReportBean.styleAttributionProfit = styleAttributionProfit;
        backtestReportBean.styleAttributionRisk = styleAttributionRisk;
        backtestReportBean.industryAttributionProfit = industryAttributionProfit;
        backtestReportBean.industryAttributionRisk = industryAttributionRisk;
        backtestReportBean.varietyAttribution = varietyAttribution;
        backtestReportBean.brisonAttributionBond = brisonAttributionBond;
        backtestReportBean.brisonAttributionStock = brisonAttributionStock;

        backtestReportBean.fundFactorsHeat = getFundFactorsHeat(backtestReportBean.combination);

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
        if (userService.getCurrentUser() == null) {
            return Collections.EMPTY_LIST;
        }

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
        /**
         * barra
         */
        else if (path == 3) {
            Map<String, Double> fundRatio = PortfolioMatlabResultGetter.getBarra(targetPath.barraFactor);
            BarraCache cache = BarraCache.getBarraCache();
            boolean res = cache.saveToCache(userService.getCurrentUser().getId(), fundRatio);
            if (!res) {
                return new ArrayList<>();
            }
            List<MiniBean> miniBeans = new ArrayList<>();
            for (String code : fundRatio.keySet()) {
                miniBeans.add(fundService.findFundNameByCode(code));
            }
            result.add(new CategoryFundBean("barra", miniBeans));
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
    public ResultMessage createCombinationByAssetAllocation(FundCombinationBean fundCombination) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResultMessage.WRONG;
        }

        CombinationAnalysis combinationAnalysis = new CombinationAnalysis(fundCombination);

        /**
         * 静态比例配置要特别处理
         */
        if (fundCombination.method == 1) {
            List<String> funds = new ArrayList<>();
            for (FundCategoryBean categoryBean : fundCombination.funds) {
                funds.addAll(categoryBean.codes);
            }

            int size = funds.size();
            double ration = 100.0 / size;

            List<FundRatioBean> fundRatioBeans = new ArrayList<>();
            for (String s : funds) {
                fundRatioBeans.add(new FundRatioBean(s, ration));
            }

            return createCombinationByUser(fundCombination.name, fundRatioBeans, fundCombination.profitRiskTarget, combinationAnalysis);
        }

        /**
         * barra
         */
        if (fundCombination.path == 3) {
            Map<String, Double> fundRatio = BarraCache.getBarraCache().findFromCache(userService.getCurrentUser().getId());
            if (fundRatio == null || fundRatio.isEmpty()) {
                return ResultMessage.FAILED;
            }

            List<FundRatioBean> beans = new ArrayList<>();
            for (Map.Entry<String, Double> entry : fundRatio.entrySet()) {
                beans.add(new FundRatioBean(entry.getKey(), entry.getValue()));
            }

            return createCombinationByUser(fundCombination.name, beans, fundCombination.profitRiskTarget, null);
        }

        Map<String, Double> result;
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

            result = calComponentWeight(codes, portfolioType, input_kind, new ArrayList<>(), uncentralize_type, fundCombination.profitRate / 100.00);
        } else return ResultMessage.INVALID;

        if (result == null || result.isEmpty()) {
            return ResultMessage.FAILED;
        }
        List<FundRatioBean> beans = new ArrayList<>();
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            beans.add(new FundRatioBean(entry.getKey(), FormatData.fixToTwoAndPercent(entry.getValue())));
        }

        return createCombinationByUser(fundCombination.name, beans, fundCombination.profitRiskTarget, combinationAnalysis);
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


    /**
     * 获取聚类分析后的热图数据
     *
     * @return
     */
    private FundFactorsHeatBean getFundFactorsHeat(List<FundRatioNameBean> fundRatioNameBeans) {
        List<String> resFunds = new ArrayList<>();
        List<String> resFactors = new ArrayList<>();
        List<FundFactorsHeatDataBean> resDatas = new ArrayList<>();

        String instruction = "";
        Map<String, String> codeNameMap = new HashMap<>();
        fundRatioNameBeans.forEach(fundRatioNameBean -> codeNameMap.put(fundRatioNameBean.code, fundRatioNameBean.name));

        //计算有效数目
        int count = 0;
        for (String code : codeNameMap.keySet()) {
            FactorResult fh = factorResultRepository.findByCodeAndFactorType(code, 'N');
            if (fh != null) {
                count++;
                instruction += String.valueOf(code) + " " +
                        fh.getBeta() + " " +
                        fh.getBtop() + " " +
                        fh.getEarningsYield() + " " +
                        fh.getGrowth() + " " +
                        fh.getLeverage() + " " +
                        fh.getLiquidity() + " " +
                        fh.getMomentum() + " " +
                        fh.getNlsize() + " " +
                        fh.getResidualvolatility() + " " +
                        fh.getSize() + " ";
            }
        }
        instruction = String.valueOf(count) + " " + instruction;
        //python排序结果
        String orderRes = PythonUser.usePy("cluster.py", instruction);
        //结果第一行为代码，下面10行为各个因子
        String orderedCodesAndAttr[] = orderRes.split("\n");
        for (String code : orderedCodesAndAttr[0].split(" ")) {
            resFunds.add(codeNameMap.get(code));
        }

        //10种因子
        String attrs[] = {"beta", "价值", "盈利能力", "成长性", "杠杆率", "流动性", "动量", "非线性市值", "波动率", "市值"};

        for (int i = 0; i < 10; i++) {
            resFactors.add(attrs[i]);
            String[] values = orderedCodesAndAttr[i].split(" ");
            for (int j = 0; j < values.length; j++) {
                resDatas.add(new FundFactorsHeatDataBean(i, j, Double.parseDouble(values[j])));
            }
        }

        return new FundFactorsHeatBean(resFunds, resFactors, resDatas);
    }
}