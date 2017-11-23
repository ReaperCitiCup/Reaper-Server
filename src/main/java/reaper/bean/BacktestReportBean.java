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
    public List<BacktestCorrelationTable> correlationCoefficientTable;

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
//    public Double beta;

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

    /**
     * 热图
     */
    public FundFactorsHeatBean fundFactorsHeat;

    /**
     * 是否成功
     */
    public Boolean isSuccess;

    public BacktestReportBean(String startDate, String endDate, String baseIndex, List<FundRatioNameBean> combination, String investmentGoal, Double intervalAnnualProfit, Double cumulativeProfit, Double finalNetValue, Double maxRetracement, Double sharpeRatio, Double volatility, List<String> mainFactors, BacktestComparisonBean cumulativeNetValueTrend, BacktestComparisonBean profitRateTrend, BacktestValueComparisonBean totalProfitRate, BacktestValueComparisonBean overProfitRate, BacktestValueComparisonBean annualProfit, BacktestValueComparisonBean profitDaysRatio, BacktestComparisonBean dailyRetracementTrend, List<BacktestCorrelationTable> correlationCoefficientTable, Double maxDayDown, Integer maxDownDays, Double annualVolatility, Double var, Double averageCorrelationCoefficient, List<FieldValueBean> styleAttributionProfit, List<FieldValueBean> styleAttributionRisk, List<FieldValueBean> industryAttributionProfit, List<FieldValueBean> industryAttributionRisk, List<FieldValueBean> varietyAttribution, List<FieldValueBean> brisonAttributionStock, List<FieldValueBean> brisonAttributionBond, FundFactorsHeatBean fundFactorsHeat, Boolean isSuccess) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.baseIndex = baseIndex;
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
        this.profitDaysRatio = profitDaysRatio;
        this.dailyRetracementTrend = dailyRetracementTrend;
        this.correlationCoefficientTable = correlationCoefficientTable;
        this.maxDayDown = maxDayDown;
        this.maxDownDays = maxDownDays;
        this.annualVolatility = annualVolatility;
        this.var = var;
        this.averageCorrelationCoefficient = averageCorrelationCoefficient;
        this.styleAttributionProfit = styleAttributionProfit;
        this.styleAttributionRisk = styleAttributionRisk;
        this.industryAttributionProfit = industryAttributionProfit;
        this.industryAttributionRisk = industryAttributionRisk;
        this.varietyAttribution = varietyAttribution;
        this.brisonAttributionStock = brisonAttributionStock;
        this.brisonAttributionBond = brisonAttributionBond;
        this.fundFactorsHeat = fundFactorsHeat;
        this.isSuccess = isSuccess;
    }

    public BacktestReportBean() {
    }

    @Override
    public String toString() {

        for (FundRatioNameBean fundRatioNameBean : combination) {
            System.out.print("Some specific values: combination=");
            System.out.println(fundRatioNameBean.code + "," + fundRatioNameBean.name + "," + fundRatioNameBean.weight);
        }

        for (ValueDateBean valueDateBean : cumulativeNetValueTrend.base) {
            System.out.print("cumulativeNetValueTrend.base=");
            System.out.println(valueDateBean.date + "," + valueDateBean.value);
        }

        for (ValueDateBean valueDateBean : cumulativeNetValueTrend.fund) {
            System.out.print("cumulativeNetValueTrend.fund=");
            System.out.println(valueDateBean.date + "," + valueDateBean.value);
        }

        for (ValueDateBean valueDateBean : profitRateTrend.base) {
            System.out.print("profitRateTrend.base=");
            System.out.println(valueDateBean.date + "," + valueDateBean.value);
        }

        for (ValueDateBean valueDateBean : profitRateTrend.fund) {
            System.out.print("profitRateTrend.fund=");
            System.out.println(valueDateBean.date + "," + valueDateBean.value);
        }

        System.out.println("totalProfitRate.base=" + totalProfitRate.baseIndex);
        System.out.println("totalProfitRate.fund=" + totalProfitRate.combination);

        System.out.println("overProfitRate.base=" + overProfitRate.baseIndex);
        System.out.println("overProfitRate.fund=" + overProfitRate.combination);

        System.out.println("annualProfit.base=" + annualProfit.baseIndex);
        System.out.println("annualProfit.fund=" + annualProfit.combination);

        System.out.println("profitDaysRatio.base=" + profitDaysRatio.baseIndex);
        System.out.println("profitDaysRatio.fund=" + profitDaysRatio.combination);

        for (ValueDateBean valueDateBean : dailyRetracementTrend.base) {
            System.out.print("dailyRetracementTrend.base=");
            System.out.println(valueDateBean.date + "," + valueDateBean.value);
        }

        for (ValueDateBean valueDateBean : dailyRetracementTrend.fund) {
            System.out.print("dailyRetracementTrend.fund=");
            System.out.println(valueDateBean.date + "," + valueDateBean.value);
        }

        for (BacktestCorrelationTable backtestCorrelationTable : correlationCoefficientTable) {
            System.out.print("correlationCoefficientTrend=");
            System.out.println(backtestCorrelationTable.fund1 + "," + backtestCorrelationTable.fund2 + "," + backtestCorrelationTable.value);
        }


        return "BacktestReportBean{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", baseIndex='" + baseIndex + '\'' +
//                ", combination=" + combination +
                ", investmentGoal='" + investmentGoal + '\'' +
                ", intervalAnnualProfit=" + intervalAnnualProfit +
                ", cumulativeProfit=" + cumulativeProfit +
                ", finalNetValue=" + finalNetValue +
                ", maxRetracement=" + maxRetracement +
                ", sharpeRatio=" + sharpeRatio +
                ", volatility=" + volatility +
                ", mainFactors=" + mainFactors +
//                ", cumulativeNetValueTrend=" + cumulativeNetValueTrend +
//                ", profitRateTrend=" + profitRateTrend +
//                ", totalProfitRate=" + totalProfitRate +
//                ", overProfitRate=" + overProfitRate +
//                ", annualProfit=" + annualProfit +
//                ", profitDaysRatio=" + profitDaysRatio +
//                ", dailyRetracementTrend=" + dailyRetracementTrend +
//                ", correlationCoefficientTrend=" + correlationCoefficientTrend +
                ", maxDayDown=" + maxDayDown +
                ", maxDownDays=" + maxDownDays +
                ", annualVolatility=" + annualVolatility +
//                ", beta=" + beta +
                ", var=" + var +
//                ", averageCorrelationCoefficient=" + averageCorrelationCoefficient +
//                ", styleAttributionProfit=" + styleAttributionProfit +
//                ", styleAttributionRisk=" + styleAttributionRisk +
//                ", industryAttributionProfit=" + industryAttributionProfit +
//                ", industryAttributionRisk=" + industryAttributionRisk +
//                ", varietyAttribution=" + varietyAttribution +
//                ", brisonAttributionStock=" + brisonAttributionStock +
//                ", brisonAttributionBond=" + brisonAttributionBond +
                '}';
    }
}
