package reaper.bean;

import java.util.List;

/**
 * Created by Feng on 2017/9/2.
 */
public class FundPerformanceBean {
    /**
     * 历任的基金
     */
    List<PerformanceDataBean> funds;
    /**
     * 其他的基金
     */
    List<PerformanceDataBean> others;

    public FundPerformanceBean(List<PerformanceDataBean> funds, List<PerformanceDataBean> others) {
        this.funds = funds;
        this.others = others;
    }
}
