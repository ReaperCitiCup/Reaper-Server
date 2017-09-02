package reaper.bean;

import java.util.List;

/**
 * Created by Feng on 2017/8/23.
 */
public class FundHistoryBean {
    /**
     * 基金id，fundcode
     */
    public String id;

    /**
     * 基金名称
     */
    public String name;

    /**
     * 基金类型，size=2，
     * 第一个是Fund中的type1，第二个是Fund中的type2
     */
    public List<String> type;

    /**
     * 基金规模
     */
    public Double scope;

    /**
     * 持有开始日期
     */
    public String startDate;

    /**
     * 持有结束日期
     */
    public String endDate;

    /**
     * 持有天数
     */
    public Integer days;

    /**
     * 任期回报
     */
    public Double returns;

    public FundHistoryBean(String id, String name, List<String> type, Double scope, String startDate, String endDate, Integer days, Double returns) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.returns = returns;
    }
}
