package reaper.service;

import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerBean;

import java.util.List;

/**
 * Created by Feng on 2017/8/23.
 */
public interface ManagerService {
    public ManagerBean findManagerById(String id);
    public List<FundHistoryBean> findFundHistoryById(String id);
}
