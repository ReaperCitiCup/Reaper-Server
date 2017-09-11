package reaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import reaper.bean.*;
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
     * @param managerId 经理id
     * @return 经理信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ManagerBean findManagerById(@PathVariable String managerId){
        return managerService.findManagerById(managerId);
    }

    /**
     * 根据经理id获得经理综合能力
     * @param managerId 经理id
     * @return 经理综合能力
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}/ability",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ManagerAbilityBean findManagerAbilityByManagerId(@PathVariable String managerId){
        return managerService.findManagerAbilityByManagerId(managerId);
    }

    /**
     * 根据经理id获得经理历史基金信息
     * @param managerId 经理id
     * @return 经理历史基金信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}/funds",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FundHistoryBean> findFundHistoryById(@PathVariable String managerId){
        return managerService.findFundHistoryById(managerId);
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
    }

    /**
     * 根据经理id获得经理现任基金收益率走势
     * @param managerId 经理id
     * @return 经理现任基金收益率走势
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}/fund-rate-trend",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<RateTrendBean> findFundRateTrendByManagerId(@PathVariable String managerId){
        return managerService.findFundRateTrendByManagerId(managerId);
    }

    /**
     * 根据经理id获得经理现任基金排名走势
     * @param managerId 经理id
     * @return 经理现任基金排名走势
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}/fund-rank-trend",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<RankTrendBean> findFundRankTrendByManagerId(@PathVariable String managerId){
        return managerService.findFundRankTrendByManagerId(managerId);
    }

    /**
     * 根据经理id获得经理任期中的基金收益
     * @param managerId 经理id
     * @return 经理任期中的基金收益
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}/fund-returns",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<ReturnBean> findFundReturnsByManagerId(@PathVariable String managerId){
        return managerService.findFundReturnsByManagerId(managerId);
    }

    /**
     * 根据经理id获得经理历任基金表现
     * @param managerId 经理id
     * @return 经理历任基金表现
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}/fund-performance",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public FundPerformanceBean findFundPerformanceByManagerId(@PathVariable String managerId){
        return managerService.findFundPerformanceByManagerId(managerId);
    }

    /**
     * 根据经理id获得经理综合表现
     * @param managerId 经理id
     * @return 经理综合表现
     * @apiNote 第一个为当前经理，剩下的为其他经理
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}/manager-performance",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ManagerPerformanceBean findManagerPerformanceByManagerId(@PathVariable String managerId){
        return managerService.findManagerPerformanceByManagerId(managerId);
    }

    /**
     * 根据经理id获得经理社会关系网络图
     * @param managerId 经理id
     * @return 经理社会关系网络图
     */
    @ResponseBody
    @RequestMapping(
            value = "/{managerId}/social-network",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
     public NetworkBean findSocialNetworkByManagerId(@PathVariable String managerId){
        return managerService.findSocialNetworkByManagerId(managerId);
    }
}
