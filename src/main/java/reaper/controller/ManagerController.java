package reaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerBean;
import reaper.model.Manager;
import reaper.service.ManagerService;

import java.util.List;

/**
 * Created by Feng on 2017/8/23.
 */

@Controller
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @ResponseBody
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ManagerBean findManagerById(@PathVariable String id){
        return managerService.findManagerById(id);
    }

    @ResponseBody
    @RequestMapping(
            value = "/{id}/funds",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FundHistoryBean> findFundHistoryById(@PathVariable String id){
        return managerService.findFundHistoryById(id);
    }
}
