package reaper.service.impl;

import org.springframework.stereotype.Service;
import reaper.bean.FieldValueBean;
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
    public FundPerformanceBean findFundPerformanceByCompanyId(String companyId) {
        return null;
    }

    @Override
    public ManagerPerformanceBean findManagerPerformanceByCompanyId(String companyId) {
        return null;
    }

    @Override
    public List<FieldValueBean> findProductStrategyByCompanyId(String companyId) {
        return null;
    }

    @Override
    public List<FieldValueBean> findAssetAllocationByCompanyId(String companyId) {
        return null;
    }

    @Override
    public List<FieldValueBean> findStyleAttributionProfitByCompanyId(String companyId) {
        return null;
    }

    @Override
    public List<FieldValueBean> findStyleAttributionRiskByCompanyId(String companyId) {
        return null;
    }

    @Override
    public List<FieldValueBean> findIndustryAttributionProfitByCompanyId(String companyId) {
        return null;
    }

    @Override
    public List<FieldValueBean> findIndustryAttributionRiskByCompanyId(String companyId) {
        return null;
    }
}
