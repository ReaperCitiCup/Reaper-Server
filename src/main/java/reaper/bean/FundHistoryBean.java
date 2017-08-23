package reaper.bean;

import java.util.List;

/**
 * Created by Feng on 2017/8/23.
 */
public class FundHistoryBean {
    public String id;
    public String name;
    public List<String> type;
    public double scope;
    public String startDate;
    public String endDate;
    public int days;
    public double returns;

    public FundHistoryBean(String id, String name, List<String> type, double scope, String startDate, String endDate, int days, double returns) {
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
