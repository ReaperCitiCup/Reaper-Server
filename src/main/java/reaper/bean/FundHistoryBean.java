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
    public String name;
    public List<String> type;
    public Double scope;
    public String startDate;
    public String endDate;
    public Integer days;
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
