package reaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reaper.bean.*;
import reaper.service.CombinationService;
import reaper.util.ResultMessage;

import java.util.List;

/**
 * @author keenan on 09/09/2017
 */
@Controller
@RequestMapping("/api")
@SuppressWarnings("Duplicates")
public class CombinationController {
    @Autowired
    private CombinationService combinationService;

    /**
     * 自定组合
     *
     * @param creationBean
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/combination",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean createCombination(@RequestBody CombinationCreationBean creationBean) {
        ResultMessage resultMessage = combinationService.createCombinationByUser(creationBean.name, creationBean.funds);
        ResultMessageBean result = new ResultMessageBean(false);

        if (resultMessage.equals(ResultMessage.SUCCESS)) {
            result.result = true;
        } else if (resultMessage.equals(ResultMessage.FAILED)) {
            result.result = false;
            result.message = "创建组合失败";
        } else if (resultMessage.equals(ResultMessage.WRONG)) {
            result.result = false;
            result.message = "用户未登录";
        }
        return result;
    }

    /**
     * 我的组合列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/combination",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<CombinationMiniBean> findCombinations() {
        return null;
    }

    /**
     * 删除组合
     */
    @ResponseBody
    @RequestMapping(
            value = "/combination/{combinationId}",
            method = RequestMethod.DELETE,
            produces = {"application/json; charset=UTF-8"}
    )
    public ResultMessageBean deleteCombination(@PathVariable Integer combinationId) {
        ResultMessage resultMessage = combinationService.deleteCombination(combinationId);
        if (resultMessage.equals(ResultMessage.SUCCESS)) {
            return new ResultMessageBean(true);
        } else if (resultMessage.equals(ResultMessage.FAILED)) {
            return new ResultMessageBean(false, "删除失败");
        } else if (resultMessage.equals(ResultMessage.WRONG)) {
            return new ResultMessageBean(false, "尚未登录");
        } else {
            return new ResultMessageBean(false, "无效操作");
        }
    }


    /**
     * 回测组合
     *
     * @param combinationId
     * @param backtestInput
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/combination/{combinationId}/backtest",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}
    )
    public BacktestReportBean backtestCombination(@PathVariable Integer combinationId, @RequestBody BacktestInputBean backtestInput) {
        return null;
    }

    /**
     * 资产配置-目标+路径
     *
     * @param assetTargetPathBean
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/asset-allocation/target-path",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}
    )
    public CategoryFundBean getTargetPath(@RequestBody AssetTargetPathBean assetTargetPathBean) {
        //TODO 已有商业组python代码，待调用
        return null;
    }

    /**
     * 资产配置-基金+组合
     *
     * @param fundCombination
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/asset-allocation/fund-combination",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}
    )
    public ResultMessageBean getFundCombination(@RequestBody FundCombinationBean fundCombination) {
        ResultMessage resultMessage = combinationService.createCombinationByAssetAllocation(fundCombination);
        ResultMessageBean result = new ResultMessageBean(false);

        if (resultMessage.equals(ResultMessage.SUCCESS)) {
            result.result = true;
        } else if (resultMessage.equals(ResultMessage.FAILED)) {
            result.result = false;
            result.message = "创建组合失败";
        } else if (resultMessage.equals(ResultMessage.WRONG)) {
            result.result = false;
            result.message = "用户未登录";
        }
        return result;
    }


}
