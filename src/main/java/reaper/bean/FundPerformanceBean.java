package reaper.bean;

/**
 * Created by Feng on 2017/9/2.
 */
public class FundPerformanceBean {
    /**
     * 基金代码
     */
    public String id;

    /**
     * 基金名称
     */
    public String name;

    /**
     * 收益率
     */
    public Double rate;

    /**
     * 风险
     */
    public Double risk;

    public FundPerformanceBean(String id, String name, Double rate, Double risk) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.risk = risk;
    }
}
