package reaper.bean;

/**
 * Created by Feng on 2017/9/10.
 */
public class PerformanceDataBean {
    /**
     * 基金代码 or 经理id
     */
    public String id;

    /**
     * 基金名称 or 经理姓名
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

    public PerformanceDataBean(String id, String name, Double rate, Double risk) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.risk = risk;
    }
}
