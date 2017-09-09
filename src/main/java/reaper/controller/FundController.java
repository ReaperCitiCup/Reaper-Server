package reaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reaper.bean.*;
import reaper.service.FundService;
import reaper.util.Page;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sorumi on 17/8/21.
 */

@Controller
@RequestMapping("/api/fund")
public class FundController {

    @Autowired
    private FundService fundService;

    /**
     * @param keyword 关键字
     * @param order   按什么排序
     * @param size    每页大小
     * @param page    第几页
     * @return Movie 分页列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/search",
            params = {"keyword", "order", "size", "page"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public Page<FundMiniBean> findFundByKeyword(@RequestParam(value = "keyword") String keyword,
                                                @RequestParam(value = "order") String order,
                                                @RequestParam(value = "size") int size,
                                                @RequestParam(value = "page") int page) {
        return fundService.findFundByKeyword(keyword, order, size, page);
    }

    /**
     * 返回基金名
     * @param code 代码
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/name",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public MiniBean findFundNameByCode(@PathVariable String code){
        return fundService.findFundNameByCode(code);
    }

    /**
     * @param code 基金代码
     * @return 基金详细信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public FundBean findFundByCode(@PathVariable String code) {
        return fundService.findFundByCode(code);
    }

    /**
     * 根据基金代码获得基金单位净值走势
     *
     * @param code 代码
     * @return 对应基金所有时间的单位净值
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/unit-net-value",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<ValueDateBean> findUnitNetValueDateByCode(@PathVariable String code) {
        return fundService.findUnitNetValueTrendByCode(code);
    }

    /**
     * 根据基金代码获得基金积累净值走势
     *
     * @param code 代码
     * @return 对应基金所有时间的积累净值
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/cumulative-net-value",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<ValueDateBean> findCumulativeNetValueDateByCode(@PathVariable String code) {
        return fundService.findCumulativeNetValueTrendByCode(code);
    }

    /**
     * 根据基金代码和时间段获得基金累计收益率走势
     *
     * @param code  代码
     * @param month 时间段（时间分为:1月、3月、6月、1年(12月)、3年(36月)、成立来(所有)，即输入为1/3/6/12/36/all）
     * @return 对应代码基金在该时间段的积累收益率
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/rate",
            params = {"month"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<ValueDateBean> findCumulativeRateByCode(@PathVariable String code, @RequestParam(value = "month") String month) {
        return fundService.findCumulativeRateTrendByCode(code, month);
    }

    /**
     * 根据基金代码获得当前资产配置
     *
     * @param code 代码
     * @return 当前资产配置
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/current-asset",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public CurrentAssetBean findCurrentAssetByCode(@PathVariable String code) {
        return fundService.findCurrentAssetByCode(code);
    }

    /**
     * 基金历史经理
     *
     * @param code 基金代码
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/managers",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<ManagerHistoryBean> findHistoryManager(@PathVariable String code) {
        return fundService.findHistoryManagerByCode(code);
    }

    @ResponseBody
    @RequestMapping(
            value = "/{code}/volatility",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<ValueDateBean> findVolatilityByCode(@PathVariable String code) {
        return Collections.emptyList();
    }

    /**
     * 詹森指数
     * @param code 代码
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/jensen-index",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<ValueDateBean> findJensenByCode(@PathVariable String code){
        return fundService.findJensenByCode(code);
    }

    /**
     * 基金对应公司
     * @param code 代码
     * @return 公司id+名字
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/company",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public MiniBean findFundCompanyByCode(@PathVariable String code){
        return fundService.findFundCompanyByCode(code);
    }

    /**
     * 基金行业归因-主动收益
     * @param code 代码
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/industry-attribution/profit",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<FieldValueBean> findIndustryAttributionProfit(@PathVariable String code){
        return fundService.findIndustryAttributionProfit(code);
    }

    /**
     * 基金行业归因-主动风险
     * @param code 代码
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/industry-attribution/risk",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<FieldValueBean> findIndustryAttributionRisk(@PathVariable String code){
        return fundService.findIndustryAttributionRisk(code);
    }

    /**
     * 基金风格归因-主动收益
     * @param code 代码
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/style-attribution/profit",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<FieldValueBean> findStyleAttributionProfit(@PathVariable String code){
        return fundService.findStyleAttributionProfit(code);
    }

    /**
     * 基金风格归因-主动风险
     * @param code 代码
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/style-attribution/risk",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<FieldValueBean> findStyleAttributionRisk(@PathVariable String code){
        return fundService.findStyleAttributionRisk(code);
    }

}
