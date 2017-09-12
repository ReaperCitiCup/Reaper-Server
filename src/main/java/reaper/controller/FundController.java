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
     * @param code 基金代码
     * @return 对应基金现任经理
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/manager",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<IdNameBean> findCurrentManagers(@PathVariable String code){
        return fundService.findCurrentManagers(code);
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

    //TODO 这一部分修改了之前的，不知道对不对，请多注意
    /**
     * 基金波动率
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/volatility",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"}
    )
    public List<ValueDateBean> findVolatilityByCode(@PathVariable String code) {
//        return Collections.emptyList();
        return fundService.findVolatility(code);
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
    public IdNameBean findFundCompanyByCode(@PathVariable String code){
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

    /**
     * 基金风险走势
     * @param code 代码
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/risk-trend",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<ValueDateBean> findRiskTrendById(@PathVariable String code){
        return fundService.findRiskTrend(code);
    }

    /**
     * 基金每日回撤
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/daily-retracement",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<ValueDateBean> findDailyRetracement(@PathVariable String code){
        return fundService.findDailyRetracement(code);
    }

    /**
     * 基金在险价值
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/value-at-risk",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<ValueDateBean> findValueAtRisk(@PathVariable String code){
        return fundService.findValueAtRisk(code);
    }

    /**
     * 基金下行波动率
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/downside-volatility",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<ValueDateBean> findDownsideVolatility(@PathVariable String code){
        return fundService.findDownsideVolatility(code);
    }

    /**
     * 基金夏普指标
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/sharpe-index",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<ValueDateBean> findSharpeIndex(@PathVariable String code){
        return fundService.findSharpeIndex(code);
    }

    /**
     * 基金特雷诺指标
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/treynor-index",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<ValueDateBean> findTreynorIndex(@PathVariable String code){
        return fundService.findTreynorIndex(code);
    }

    /**
     * 基金业绩持续性指标
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/performance-index",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public PerformanceIndexBean findPerformanceIndex(@PathVariable String code){
        return fundService.findPerformanceIndex(code);
    }

    /**
     * 基金品种归因
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/variety-attribution",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FieldValueBean> findVarietyAttribution(@PathVariable String code){
        return fundService.findVarietyAttribution(code);
    }

    /**
     * 基金Brison归因-基于股票持仓
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/brison-attribution/stock",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FieldValueBean> findBrisonAttributionStock(@PathVariable String code){
        return fundService.findBrisonAttributionStock(code);
    }

    /**
     * 基金Brison归因-基于债券持仓
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/brison-attribution/bond",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<FieldValueBean> findBrisonAttributionBond(@PathVariable String code){
        return fundService.findBrisonAttributionBond(code);
    }

    /**
     * 基金择时能力
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/choose-time",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ChooseBean findChooseTime(@PathVariable String code){
        return fundService.findChooseTime(code);
    }

    /**
     * 基金择股能力
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/choose-stock",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ChooseBean findChooseStock(@PathVariable String code){
        return fundService.findChooseStock(code);
    }

    /**
     * 当前基金经理历任基金表现
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/fund-performance",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public FundPerformanceBean findFundPerformance(@PathVariable String code){
        return fundService.findFundPerformance(code);
    }

    /**
     * 当前基金经理表现
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/manager-performance",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ManagerPerformanceBean findManagerPerformance(@PathVariable String code){
        return fundService.findManagerPerformance(code);
    }

    /**
     * 基金舆情分析
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/pubic-opinion",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<PublicOpinionBean> findPublicOpinion(@PathVariable String code){
        return fundService.findPublicOpinion(code);
    }

    /**
     * 基金持仓关联网络图
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/{code}/position-network",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public NetworkBean findPositionNetwork(@PathVariable String code){
        return fundService.findPositionNetwork(code);
    }

}
