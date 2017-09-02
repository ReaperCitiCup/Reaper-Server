package reaper.service;

import reaper.bean.*;
import reaper.util.Page;

import java.util.List;

/**
 * Created by Sorumi on 17/8/21.
 */
public interface FundService {
    /**
     * @param keyword  关键字
     * @param order    按什么排序
     * @param size     每页大小
     * @param page     第几页
     * @return Movie 分页列表
     */
    public Page<FundMiniBean> findFundByKeyword(String keyword, String order, int size, int page);

    /**
     * @param code 基金代码
     * @return 基金详细信息
     */
    public FundBean findFundByCode(String code);

    /**
     * 根据基金代码获得基金单位净值走势
     * @param code 代码
     * @return 对应基金所有时间的单位净值
     */
    public List<NetValueDateBean> findUnitNetValueTrendByCode(String code);

    /**
     * 根据基金代码获得基金积累净值走势
     * @param code 代码
     * @return 对应基金所有时间的积累净值
     */
    public List<NetValueDateBean> findCumulativeNetValueTrendByCode(String code);

    /**
     * 根据基金代码和时间段获得基金累计收益率走势
     * @param code 代码
     * @param month 时间段（时间分为:1月、3月、6月、1年(12月)、3年(36月)、成立来(所有)，即输入为1/3/6/12/36/all）
     * @return 对应代码基金在该时间段的积累收益率
     */
    public List<NetValueDateBean> findCumulativeRateTrendByCode(String code,String month);

    /**
     * 基金历史经理数据
     * @param code 基金代码
     * @return 历史经理情况
     */
    public List<HistoryManagerBean> findHistoryManagersByCode(String code);

    /**
     * 根据基金代码获得当前资产配置
     * @param code 代码
     * @return 当前资产配置
     */
    public CurrentAssetBean findCurrentAssetByCode(String code);

    /**
     * 基金历史经理
     * @param code 基金代码
     * @return
     */
    public List<ManagerHistoryBean> findHistoryManagerByCode(String code);
}
