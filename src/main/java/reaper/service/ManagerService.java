package reaper.service;

import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerAbilityBean;
import reaper.bean.ManagerBean;
import reaper.bean.*;

import java.util.List;

/**
 * Created by Feng on 2017/8/23.
 */
public interface ManagerService {
    /**
     * 根据经理id获得经理信息
     * @param id 经理id
     * @return 经理信息
     */
    public ManagerBean findManagerById(String id);

    /**
     * 根据经理id获得经理历史基金信息
     * @param id 经理id
     * @return 经理历史基金信息
     */
    public List<FundHistoryBean> findFundHistoryById(String id);

    /**
     * 根据经理id获得经理任期中的基金收益
     * @param managerId 经理id
     * @return 经理任期中的基金收益
     */
    public List<ReturnBean> findFundReturnsByManagerId(String managerId);

    /**
     * 根据经理id获得经理现任基金排名
     * @param managerId 经理id
     * @return 经理现任基金排名
     */
    public List<RankBean> findFundRankByManagerId(String managerId);

    /**
     * 根据经理id获得经理现任基金收益率走势
     * @param managerId 经理id
     * @return 经理现任基金收益率走势
     */
    public List<RateTrendBean> findFundRateTrendByManagerId(String managerId);

    /**
     * 根据经理id获得经理现任基金排名走势
     * @param managerId 经理id
     * @return 经理现任基金排名走势
     */
    public List<RankTrendBean> findFundRankTrendByManagerId(String managerId);

    /**
     * 根据经理id获得经理历任基金表现
     * @param managerId 经理id
     * @return 经理历任基金表现
     */
    public FundPerformanceBean findFundPerformanceByManagerId(String managerId);

    /**
     * 根据经理id获得经理综合表现
     * @param managerId 经理id
     * @return 经理综合表现
     * @apiNote 第一个为当前经理，剩下的为其他经理
     */
    public ManagerPerformanceBean findManagerPerformanceByManagerId(String managerId);

    /**
     * 根据经理id获得经理综合能力
     * @param managerId 经理id
     * @return 经理综合能力
     */
    public ManagerAbilityBean findManagerAbilityByManagerId(String managerId);

    public NetworkBean findSocialNetworkByManagerId(String managerId);
}
