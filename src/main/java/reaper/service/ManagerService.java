package reaper.service;

import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerAbilityBean;
import reaper.bean.ManagerBean;

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
     * @param id 经理id
     * @return 经理能力值
     */
    public ManagerAbilityBean findManagerAbilityById(String id);
}
