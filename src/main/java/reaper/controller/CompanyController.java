package reaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/fund-performance",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FundPerformanceBean> findFundPerformanceByCompanyId(@PathVariable String companyId){
        return companyService.findFundPerformanceByCompanyId(companyId);
    }

    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/manager-performance",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<ManagerPerformanceBean> findManagerPerformanceByCompanyId(@PathVariable String companyId){
        return companyService.findManagerPerformanceByCompanyId(companyId);
    }
}
