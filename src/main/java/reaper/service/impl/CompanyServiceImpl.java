package reaper.service.impl;

import org.springframework.stereotype.Service;
import reaper.bean.FundPerformanceBean;
import reaper.bean.ManagerPerformanceBean;
import reaper.service.CompanyService;

import java.util.List;

/**
 * Created by Feng on 2017/9/2.
 */

@Service
public class CompanyServiceImpl implements CompanyService {
    @Override
    public List<FundPerformanceBean> findFundPerformanceByCompanyId(String companyId) {
        return null;
    }

    @Override
    public List<ManagerPerformanceBean> findManagerPerformanceByCompanyId(String companyId) {
        return null;
    }
}
