package reaper.service;

import reaper.bean.FundPerformanceBean;
import reaper.bean.ManagerPerformanceBean;

import java.util.List;

/**
 * Created by Feng on 2017/9/2.
 */
public interface CompanyService {
    /**
     * 根据公司id获得公司旗下基金表现
     * @param companyId 公司id
     * @return 公司旗下基金表现
     */
    public List<FundPerformanceBean> findFundPerformanceByCompanyId(String companyId);

    /**
     * 根据公司id获得公司旗下经理表现
     * @param companyId 公司id
     * @return 公司旗下经理表现
     */
    public List<ManagerPerformanceBean> findManagerPerformanceByCompanyId(String companyId);
}
