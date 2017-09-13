package reaper.service.impl;

import Asset_Allocation.Asset_Allocation;
import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reaper.bean.*;
import reaper.model.Combination;
import reaper.model.User;
import reaper.repository.CombinationRepository;
import reaper.repository.FundNetValueRepository;
import reaper.service.CombinationService;
import reaper.service.UserService;
import reaper.util.PyAnalysisResult;
import reaper.util.PythonUser;
import reaper.util.ResultMessage;

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
    private UserService userService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final String FILE_BACK_ANALYSIS = "backtest_analysis.py";
    private static final String FILE_TARGET_PATH = "target_path.py";

    /**
     * 自定创建组合
     * 用户已登录验证，从登录用户中获得用户 id
     * 组合列表不为空，且配比之和为100
     *
     * @param name  组合名
     * @param funds 组合列表
     * @return
     */
    @Override
    public ResultMessage createCombinationByUser(String name, List<FundRatioBean> funds) {
        User user = userService.getCurrentUser();
        if (user == null) return ResultMessage.WRONG;
        StringBuilder fundsBuilder = new StringBuilder();
        StringBuilder weightsBuilder = new StringBuilder();

        for (int i = 0; i < funds.size(); i++) {
            FundRatioBean fundRatioBean = funds.get(i);
            String id = (i == funds.size() - 1) ? fundRatioBean.id : fundRatioBean.id + "|";
            String ratio = (i == funds.size() - 1) ? String.valueOf(fundRatioBean.ratio) : fundRatioBean.ratio + "|";
            fundsBuilder.append(id);
            weightsBuilder.append(ratio);
        }

        Combination combination = new Combination(user.getId(), name, fundsBuilder.toString(), weightsBuilder.toString());
        try {
            combinationRepository.save(combination);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 我的组合列表
     * 用户已登录验证，从登录用户中获得用户 id
     *
     * @return
     */
    @Override
    //TODO 考虑每天存起来
    //TODO 所有百分比都乘以100，保留两位小数
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
            String[] funds = combination.getFunds().split("|");
            String[] weights = combination.getWeights().split("|");
            PyAnalysisResult result_withoutRange = getBasicFactors(Arrays.asList(funds));

            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.set(year - 1, month, day);
            Date newday = calendar.getTime();
            PyAnalysisResult result_withRange = getBasicFactors(Arrays.asList(funds), simpleDateFormat.format(date), simpleDateFormat.format(newday));

            // 年化收益率
            double annualProfit = 0.0;
            for (int i = 0; i < funds.length; i++) {
                String code = funds[i];
                annualProfit += (result_withoutRange.nhsyl.get(code) * Double.valueOf(weights[i]));
            }
            miniBean.annualProfit = annualProfit;

            // 平均相关系数
            double sum = 0.0;
            for (PyAnalysisResult.CorrelationCoefficient c : result_withRange.pjxgxs) {
                sum += c.getCc();
            }
            double correlationCoefficient = sum / result_withRange.pjxgxs.size();
            miniBean.correlationCoefficient = correlationCoefficient;

            // 最新收益率
            double newProfit = 0.0;
            for (int i = 0; i < funds.length; i++) {
                newProfit += (fundNetValueRepository.findFirstByCodeOrderByDateDesc(funds[i]).getDailyRate() * Double.valueOf(weights[i]));
            }
            miniBean.newProfit = newProfit;

            combinationMiniBeans.add(miniBean);
        }

        return combinationMiniBeans;
    }

    /**
     * 删除组合
     *删除组合
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
    public BacktestReportBean backtestCombination(Integer combinationId, String startDate, String endDate, String baseIndex) {
        return null;
    }

    /**
     * 资产配置-选择目标及路径
     *
     * @param targetPath 目标及路径
     * @return
     */
    @Override
    public CategoryFundBean findFundsByTargetAndPath(AssetTargetPathBean targetPath) {
        return null;
    }

    /**
     * 资产配置-选择基金生成组合
     *
     * @param fundCombination 组合
     * @return
     */
    @Override
    public ResultMessage createCombinationByAssetAllocation(FundCombinationBean fundCombination) {
//        Map<String, Double> weight = calComponentWeight(fundCombination.funds, fundCombination.method);
//        if (weight == null || weight.isEmpty()) {
//            return ResultMessage.FAILED;
//        }
//        List<FundRatioBean> beans = new ArrayList<>();
//        for (Map.Entry<String, Double> entry : weight.entrySet()) {
//            beans.add(new FundRatioBean(entry.getKey(), entry.getValue()));
//        }
//        return createCombinationByUser(fundCombination.name, beans);
        return ResultMessage.FAILED;
    }

    /**
     * 创建组合时获得各基金的权重
     *
     * @param codes         基金代码
     * @param portfolioType 分散化方法类型
     * @return <基金代码, 权重(未百分化的double)>
     */
    //TODO 更改，参考test11.java
    private Map<String, Double> calComponentWeight(List<String> codes, int portfolioType) {
        Map<String, Double> resultMap = new HashMap<>();
        Object[] result = null;
        Asset_Allocation assetAllocation = null;
        MWCharArray mwCharArray = null;
        MWNumericArray mwNumericArray = null;
        // small -> large
        Collections.sort(codes);
        String[] strings = codes.toArray(new String[codes.size()]);

        try {
            mwCharArray = new MWCharArray(strings);
            mwNumericArray = new MWNumericArray(portfolioType);
            assetAllocation = new Asset_Allocation();

            result = assetAllocation.asset_arrangement(1, mwCharArray, mwNumericArray);

            String[] res = null;
            if (result != null || result.length != 0) {
                res = result[0].toString().replaceAll("[ ]+", " ").split(" ");
            }

            if (res == null || res.length != codes.size()) {
                return Collections.EMPTY_MAP;
            }

            for (int i = 0; i < codes.size(); i++) {
                resultMap.put(codes.get(i), Double.valueOf(res[i]));
            }
            return resultMap;
        } catch (MWException e) {
            return Collections.EMPTY_MAP;
        } finally {
            MWArray.disposeArray(result);
            MWArray.disposeArray(mwCharArray);
            MWArray.disposeArray(mwNumericArray);
            if (assetAllocation != null) {
                assetAllocation.dispose();
            }
            assetAllocation = null;
        }
    }

    /**
     * 调用python代码
     * 可得到年化收益率，年化波动率，在险价值，收益率序列的下行标准差，夏普比率，beta，特雷诺指数，择股系数，择时系数，平均相关系数
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
     * 调用python代码
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
     * @param lamda
     * @param count
     * @param sqlkind
     * @param type_kind
     * @param factor_kind
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
}
