package reaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerAbilityBean;
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

    /**
     * 根据经理id获得经理基本信息
     * @param id 经理id
     * @return 经理信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ManagerBean findManagerById(@PathVariable String id){
        return managerService.findManagerById(id);
    }

    /**
     * 根据经理id获得经理综合能力
     * @param managerId 经理id
     * @return 经理综合能力
     */
    @ResponseBody
    @RequestMapping(
            value = "/{id}/ability",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ManagerAbilityBean findManagerAbilityByManagerId(@PathVariable String managerId){
        return managerService.findManagerAbilityByManagerId(managerId);
    }

    /**
     * 根据经理id获得经理历史基金信息
     * @param id 经理id
     * @return 经理历史基金信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/{id}/funds",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FundHistoryBean> findFundHistoryById(@PathVariable String id){
        return managerService.findFundHistoryById(id);
    }

    /**
     * 根据经理id获得经理现任基金排名
     * @param managerId 经理id
     * @return 经理现任基金排名
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}/fund-rank",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<RankBean> findFundRankByManagerId(@PathVariable String managerId){
        return managerService.findFundRankByManagerId(managerId);
    /**
     * @param id 经理Id
     * @return 经理能力雷达图数据
     */
    @ResponseBody
    @RequestMapping(
            value = "/{id}/ability",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public ManagerAbilityBean findManagerAbilityByManagerId(@PathVariable String id){
        return managerService.findManagerAbilityById(id);
    }
}
