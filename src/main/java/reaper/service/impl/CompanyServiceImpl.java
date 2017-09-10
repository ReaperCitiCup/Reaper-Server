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
    /**
     * 根据公司id获得公司旗下基金表现
     * @param companyId 公司id
     * @return 公司旗下基金表现
     */
    @Override
    public FundPerformanceBean findFundPerformanceByCompanyId(String companyId) {
        return null;
    }

    /**
     * 根据公司id获得公司旗下经理表现
     * @param companyId 公司id
     * @return 公司旗下经理表现
     */
    @Override
    public ManagerPerformanceBean findManagerPerformanceByCompanyId(String companyId) {
        return null;
    }

    /**
     * 根据公司id获得公司产品分布策略
     * @param companyId 公司id
     * @return 公司产品分布策略
     */
    @Override
    public List<FieldValueBean> findProductStrategyByCompanyId(String companyId) {
        return null;
    }

    /**
     * 根据公司id获得公司资产配置行业占比
     * @param companyId 公司id
     * @return 公司资产配置行业占比
     */
    @Override
    public List<FieldValueBean> findAssetAllocationByCompanyId(String companyId) {
        return null;
    }

    /**
     * 根据公司id获得公司风格归因-主动收益
     * @param companyId 公司id
     * @return 公司风格归因-主动收益
     */
    @Override
    public List<FieldValueBean> findStyleAttributionProfitByCompanyId(String companyId) {
        return null;
    }

    /**
     * 根据公司id获得公司风格归因-主动风险
     * @param companyId 公司id
     * @return 公司风格归因-主动风险
     */
    @Override
    public List<FieldValueBean> findStyleAttributionRiskByCompanyId(String companyId) {
        return null;
    }

    /**
     * 根据公司id获得公司行业归因-主动收益
     * @param companyId 公司id
     * @return 公司行业归因-主动收益
     */
    @Override
    public List<FieldValueBean> findIndustryAttributionProfitByCompanyId(String companyId) {
        return null;
    }

    /**
     * 根据公司id获得公司行业归因-主动风险
     * @param companyId 公司id
     * @return 公司行业归因-主动风险
     */
    @Override
    public List<FieldValueBean> findIndustryAttributionRiskByCompanyId(String companyId) {
        return null;
    }
}
