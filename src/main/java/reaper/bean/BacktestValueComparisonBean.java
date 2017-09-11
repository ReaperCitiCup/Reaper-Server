package reaper.bean;

/**
 * 组合和基准的比较
 *
 * @author keenan on 11/09/2017
 */
public class BacktestValueComparisonBean {
    /**
     * 组合的值
     */
    public Double combination;

    /**
     * 基准指标的值
     */
    public Double baseIndex;

    public BacktestValueComparisonBean(Double combination, Double baseIndex) {
        this.combination = combination;
        this.baseIndex = baseIndex;
    }

    public BacktestValueComparisonBean() {
    }
}
