package reaper.service;

import reaper.bean.*;
import reaper.model.CombinationAnalysis;
import reaper.util.ResultMessage;

import java.util.List;

/**
 * @author keenan on 08/09/2017
 */
public interface CombinationService {

    /**
     * 自定创建组合
     * 用户已登录验证，从登录用户中获得用户 id
     * 组合列表不为空，且配比之和为100
     *
     * @param name  组合名
     * @param funds 组合列表
     * @return
     */
    public ResultMessage createCombinationByUser(String name, List<FundRatioBean> funds, Integer profitRisk, CombinationAnalysis combinationAnalysis);

    /**
     * 我的组合列表
     * 用户已登录验证，从登录用户中获得用户 id
     *
     * @return
     */
    public List<CombinationMiniBean> findCombinations();

    /**
     * 删除组合
     *
     * @param id 组合id
     * @return
     */
    public ResultMessage deleteCombination(Integer id);

    /**
     * 回测组合
     * @param combinationId
     * @param startDate
     * @param endDate
     * @param baseIndex 基准指标
     * @return
     */
    public BacktestReportBean backtestCombination(Integer combinationId, String startDate, String endDate, String baseIndex) throws java.text.ParseException;

    /**
     * 资产配置-选择目标及路径
     *
     * @param targetPath 目标及路径
     * @return
     */
    public List<CategoryFundBean> findFundsByTargetAndPath(AssetTargetPathBean targetPath);

    /**
     * 资产配置-选择基金生成组合
     *
     * @param fundCombination 组合
     * @return
     */
    public ResultMessage createCombinationByAssetAllocation(FundCombinationBean fundCombination);

}
