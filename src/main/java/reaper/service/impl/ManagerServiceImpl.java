package reaper.service.impl;

import org.springframework.stereotype.Service;
import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerBean;
import reaper.service.ManagerService;

import java.util.List;

/**
 * Created by Feng on 2017/8/23.
 */

@Service
public class ManagerServiceImpl implements ManagerService {
    @Override
    public ManagerBean findManagerById(String id) {
        return null;
    }

    @Override
    public List<FundHistoryBean> findFundHistoryById(String id) {
        return null;
    }
}
