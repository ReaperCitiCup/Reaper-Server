package reaper.service;

import reaper.bean.AssetTargetPathBean;
import reaper.bean.CategoryFundBean;
import reaper.bean.CombinationMiniBean;
import reaper.bean.FundRatioBean;
import reaper.util.ResultMessage;

import java.time.LocalDate;
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
    public ResultMessage createCombinationByUser(String name, List<FundRatioBean> funds);

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
    public ResultMessage deleteCombination(String id);

    /**
     * 回测组合
     *
     * @param id        组合id
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public ResultMessage backtestCombination(String id, LocalDate startDate, LocalDate endDate);

    /**
     * 资产配置-选择目标及路径
     *
     * @param targetPath 目标及路径
     * @return
     */
    public CategoryFundBean findFundsByTargetAndPath(AssetTargetPathBean targetPath);

    /**
     * 资产配置-选择基金生成组合
     *
     * @param name  组合名
     * @param codes 基金代码
     * @return
     */
    public ResultMessage createCombinationByAssetAllocation(String name, List<String> codes);
}
