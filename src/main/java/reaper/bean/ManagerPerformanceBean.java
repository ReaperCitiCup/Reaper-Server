package reaper.bean;

/**
 * Created by Feng on 2017/9/2.
 */

/**
 * 第一个为当前经理，剩下的为其他经理
 */
public class ManagerPerformanceBean {
    /**
     * 经理id
     */
    public String id;

    /**
     * 经理姓名
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

    public ManagerPerformanceBean(String id, String name, Double rate, Double risk) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.risk = risk;
    }
}
