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
public class CombinationController {
    @Autowired
    private CombinationService combinationService;

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
            result.message = "组合数据错误";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(
            value = "/combination",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<CombinationMiniBean> findCombinations() {
        return null;
    }

    @ResponseBody
    @RequestMapping(
            value = "/combination/{combinationId}/backtest",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}
    )
    //TODO response body undefined
    public Object backtestCombination(@PathVariable String combinationId, @RequestBody BacktestDateBean backtestDateBean) {
        return null;
    }

    @ResponseBody
    @RequestMapping(
            value = "/api/asset-allocation/target-path",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}
    )
    public CategoryFundBean getTargetPath(@RequestBody AssetTargetPathBean assetTargetPathBean) {
        return null;
    }

    @ResponseBody
    @RequestMapping(
            value = "/api/asset-allocation/fund-combination",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}
    )
    public ResultMessageBean getFundCombination(@RequestBody FundCombinationBean fundCombination) {
        return null;
    }


}
