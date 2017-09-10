package reaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import reaper.bean.FieldValueBean;
import reaper.bean.FundPerformanceBean;
import reaper.bean.ManagerPerformanceBean;
import reaper.service.CompanyService;

import java.util.List;

/**
 * Created by Feng on 2017/9/2.
 */

@Controller
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 根据公司id获得公司旗下基金表现
     * @param companyId 公司id
     * @return 公司旗下基金表现
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/fund-performance",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public FundPerformanceBean findFundPerformanceByCompanyId(@PathVariable String companyId){
        return companyService.findFundPerformanceByCompanyId(companyId);
    }

    /**
     * 根据公司id获得公司旗下经理表现
     * @param companyId 公司id
     * @return 公司旗下经理表现
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/manager-performance",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ManagerPerformanceBean findManagerPerformanceByCompanyId(@PathVariable String companyId){
        return companyService.findManagerPerformanceByCompanyId(companyId);
    }

    /**
     * 根据公司id获得公司产品分布策略
     * @param companyId 公司id
     * @return 公司产品分布策略
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/product-strategy",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FieldValueBean> findProductStrategyByCompanyId(@PathVariable String companyId){
        return companyService.findProductStrategyByCompanyId(companyId);
    }

    /**
     * 根据公司id获得公司资产配置行业占比
     * @param companyId 公司id
     * @return 公司资产配置行业占比
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/asset-allocation",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FieldValueBean> findAssetAllocationByCompanyId(@PathVariable String companyId){
        return companyService.findAssetAllocationByCompanyId(companyId);
    }

    /**
     * 根据公司id获得公司风格归因-主动收益
     * @param companyId 公司id
     * @return 公司风格归因-主动收益
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/style-attribution/profit",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FieldValueBean> findStyleAttributionProfitByCompanyId(@PathVariable String companyId){
        return companyService.findStyleAttributionProfitByCompanyId(companyId);
    }

    /**
     * 根据公司id获得公司风格归因-主动风险
     * @param companyId 公司id
     * @return 公司风格归因-主动风险
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/style-attribution/risk",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FieldValueBean> findStyleAttributionRiskByCompanyId(@PathVariable String companyId){
        return companyService.findStyleAttributionRiskByCompanyId(companyId);
    }

    /**
     * 根据公司id获得公司行业归因-主动收益
     * @param companyId 公司id
     * @return 公司行业归因-主动收益
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/industry-attribution/profit",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FieldValueBean> findIndustryAttributionProfitByCompanyId(@PathVariable String companyId){
        return companyService.findIndustryAttributionProfitByCompanyId(companyId);
    }

    /**
     * 根据公司id获得公司行业归因-主动风险
     * @param companyId 公司id
     * @return 公司行业归因-主动风险
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/industry-attribution/risk",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FieldValueBean> findIndustryAttributionRiskByCompanyId(@PathVariable String companyId){
        return companyService.findIndustryAttributionRiskByCompanyId(companyId);
    }
}
