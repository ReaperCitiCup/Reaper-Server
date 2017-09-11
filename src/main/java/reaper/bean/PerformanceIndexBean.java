package reaper.bean;

/**
 * Created by Feng on 2017/9/10.
 */
public class PerformanceIndexBean {
    /**
     * 连续性指标
     */
    public Double sustainabilityIndex;

    /**
     * 连输期数/总期数
     */
    public Double loseDayRatio;

    /**
     * 连赢期数/总期数
     */
    public Double winDayRatio;

    public PerformanceIndexBean(Double sustainabilityIndex, Double loseDayRatio, Double winDayRatio) {
        this.sustainabilityIndex = sustainabilityIndex;
        this.loseDayRatio = loseDayRatio;
        this.winDayRatio = winDayRatio;
    }
}
