package reaper.service;

import reaper.bean.FieldValueBean;
import reaper.bean.FundPerformanceBean;
import reaper.bean.ManagerPerformanceBean;

import java.awt.*;
import java.io.File;
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
    public FundPerformanceBean findFundPerformanceByCompanyId(String companyId);

    /**
     * 根据公司id获得公司旗下经理表现
     * @param companyId 公司id
     * @return 公司旗下经理表现
     */
    public ManagerPerformanceBean findManagerPerformanceByCompanyId(String companyId);

    /**
     * 根据公司id获得公司资产配置行业占比
     * @param companyId 公司id
     * @return 公司资产配置行业占比
     */
    public List<FieldValueBean> findAssetAllocationByCompanyId(String companyId);

    /**
     * 根据公司id获得公司风格归因-主动收益
     * @param companyId 公司id
     * @return 公司风格归因-主动收益
     */
    public List<FieldValueBean> findStyleAttributionProfitByCompanyId(String companyId);

    /**
     * 根据公司id获得公司风格归因-主动风险
     * @param companyId 公司id
     * @return 公司风格归因-主动风险
     */
    public List<FieldValueBean> findStyleAttributionRiskByCompanyId(String companyId);

    /**
     * 根据公司id获得公司行业归因-主动收益
     * @param companyId 公司id
     * @return 公司行业归因-主动收益
     */
    public List<FieldValueBean> findIndustryAttributionProfitByCompanyId(String companyId);

    /**
     * 根据公司id获得公司行业归因-主动风险
     * @param companyId 公司id
     * @return 公司行业归因-主动风险
     */
    public List<FieldValueBean> findIndustryAttributionRiskByCompanyId(String companyId);

}
