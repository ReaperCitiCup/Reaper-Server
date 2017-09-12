package reaper.bean;

import java.util.List;

/**
 * 回测报告
 *
 * @author keenan on 11/09/2017
 */
public class BacktestReportBean {
    public String startDate;
    public String endDate;

    /**
     * 基准
     */
    public String baseIndex;

    /**
     * 综合评分
     */
    public Integer score;

    /**
     * 超越同级产品数
     */
    public Integer transcendQuantity;

    /**
     * 基金组合排名
     */
    public Integer rank;

    /**
     * 基金组成
     */
    public List<FundRatioNameBean> combination;

    /**
     * 投资目标
     */
    public String investmentGoal;

    /**
     * 区间年化收益
     */
    public Double intervalAnnualProfit;

    /**
     * 累计收益
     */
    public Double cumulativeProfit;

    /**
     * 最终净值
     */
    public Double finalNetValue;

    /**
     * 最大回撤
     */
    public Double maxRetracement;

    /**
     * 夏普比率
     */
    public Double sharpeRatio;

    /**
     * 波动率
     */
    public Double volatility;

    /**
     * 主要的三个因子
     */
    public List<String> mainFactors;

    /**
     * 【图】累计净值
     */
    public BacktestComparisonBean cumulativeNetValueTrend;

    /**
     * 【图】收益率
     */
    public BacktestComparisonBean profitRateTrend;

    /**
     * 总收益率 比较
     */
    public BacktestValueComparisonBean totalProfitRate;

    /**
     * 超额收益率 比较
     */
    public BacktestValueComparisonBean overProfitRate;

    /**
     * 年化收益 比较
     */
    public BacktestValueComparisonBean annualProfit;

    /**
     * 最大月度收益 比较
     */
    public BacktestValueComparisonBean maxMonthProfit;

    /**
     * 盈利天占比 比较
     */
    public BacktestValueComparisonBean profitDaysRatio;

    /**
     * 【图】每日回撤
     */
    public BacktestComparisonBean dailyRetracementTrend;

    /**
     * 【表】相关系数
     */
    public BacktestCorrelationTable correlationCoefficientTrend;

    /**
     * 最大单日跌幅
     */
    public Double maxDayDown;

    /**
     * 最大连跌天数
     */
    public Integer maxDownDays;

    /**
     * 年化波动率
     */
    public Double annualVolatility;

    /**
     * beta
     */
    public Double beta;

    /**
     * var
     */
    public Double var;

    /**
     * 平均相关系数
     */
    public Double averageCorrelationCoefficient;

    /**
     * 风格归因-收益
     */
    public List<FieldValueBean> styleAttributionProfit;

    /**
     * 风格归因-风险
     */
    public List<FieldValueBean> styleAttributionRisk;

    /**
     * 行业归因-收益
     */
    public List<FieldValueBean> industryAttributionProfit;

    /**
     * 行业归因-风险
     */
    public List<FieldValueBean> industryAttributionRisk;

    /**
     * 品种归因
     */
    public List<FieldValueBean> varietyAttribution;

    /**
     * Brison归因-股票
     */
    public List<FieldValueBean> brisonAttributionStock;

    /**
     * Brison归因-债券
     */
    public List<FieldValueBean> brisonAttributionBond;

    public BacktestReportBean(String startDate, String endDate, String baseIndex, Integer score, Integer transcendQuantity, Integer rank, List<FundRatioNameBean> combination, String investmentGoal, Double intervalAnnualProfit, Double cumulativeProfit, Double finalNetValue, Double maxRetracement, Double sharpeRatio, Double volatility, List<String> mainFactors, BacktestComparisonBean cumulativeNetValueTrend, BacktestComparisonBean profitRateTrend, BacktestValueComparisonBean totalProfitRate, BacktestValueComparisonBean overProfitRate, BacktestValueComparisonBean annualProfit, BacktestValueComparisonBean maxMonthProfit, BacktestValueComparisonBean profitDaysRatio, BacktestComparisonBean dailyRetracementTrend, BacktestCorrelationTable correlationCoefficientTrend, Double maxDayDown, Integer maxDownDays, Double annualVolatility, Double beta, Double var, Double averageCorrelationCoefficient, List<FieldValueBean> styleAttributionProfit, List<FieldValueBean> styleAttributionRisk, List<FieldValueBean> industryAttributionProfit, List<FieldValueBean> industryAttributionRisk, List<FieldValueBean> varietyAttribution, List<FieldValueBean> brisonAttributionStock, List<FieldValueBean> brisonAttributionBond) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.baseIndex = baseIndex;
        this.score = score;
        this.transcendQuantity = transcendQuantity;
        this.rank = rank;
        this.combination = combination;
        this.investmentGoal = investmentGoal;
        this.intervalAnnualProfit = intervalAnnualProfit;
        this.cumulativeProfit = cumulativeProfit;
        this.finalNetValue = finalNetValue;
        this.maxRetracement = maxRetracement;
        this.sharpeRatio = sharpeRatio;
        this.volatility = volatility;
        this.mainFactors = mainFactors;
        this.cumulativeNetValueTrend = cumulativeNetValueTrend;
        this.profitRateTrend = profitRateTrend;
        this.totalProfitRate = totalProfitRate;
        this.overProfitRate = overProfitRate;
        this.annualProfit = annualProfit;
        this.maxMonthProfit = maxMonthProfit;
        this.profitDaysRatio = profitDaysRatio;
        this.dailyRetracementTrend = dailyRetracementTrend;
        this.correlationCoefficientTrend = correlationCoefficientTrend;
        this.maxDayDown = maxDayDown;
        this.maxDownDays = maxDownDays;
        this.annualVolatility = annualVolatility;
        this.beta = beta;
        this.var = var;
        this.averageCorrelationCoefficient = averageCorrelationCoefficient;
        this.styleAttributionProfit = styleAttributionProfit;
        this.styleAttributionRisk = styleAttributionRisk;
        this.industryAttributionProfit = industryAttributionProfit;
        this.industryAttributionRisk = industryAttributionRisk;
        this.varietyAttribution = varietyAttribution;
        this.brisonAttributionStock = brisonAttributionStock;
        this.brisonAttributionBond = brisonAttributionBond;
    }

    public BacktestReportBean() {
    }
}
