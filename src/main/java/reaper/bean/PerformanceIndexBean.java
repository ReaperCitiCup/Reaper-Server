package reaper.bean;

import reaper.model.CPR;
import reaper.util.FormatData;

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

    public PerformanceIndexBean(CPR cpr) {
        sustainabilityIndex = FormatData.fixToTwo(cpr.getCPR());
        loseDayRatio = FormatData.fixToTwoAndPercent(Double.valueOf(cpr.getLL())/cpr.getTotal());
        winDayRatio = FormatData.fixToTwoAndPercent(Double.valueOf(cpr.getWW())/cpr.getTotal());
    }
}
