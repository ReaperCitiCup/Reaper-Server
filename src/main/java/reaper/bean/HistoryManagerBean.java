package reaper.bean;

import reaper.model.FundHistory;

/**
 * 基金历史经理情况
 */
public class HistoryManagerBean {
    /**
     * 经理id
     */
    public String id;

    /**
     * 名字
     */
    public String name;

    /**
     * 开始日期
     */
    public String startDate;

    /**
     * 结束日期
     */
    public String endDate;

    /**
     * 天数
     */
    public int days;

    /**
     * 任期回报
     */
    public double returns;

    public HistoryManagerBean(String id, String name, String startDate, String endDate, int days, double returns) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.returns = returns;
    }
}
