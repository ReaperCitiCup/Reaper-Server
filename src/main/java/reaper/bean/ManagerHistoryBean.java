package reaper.bean;

public class ManagerHistoryBean {
    public String id;
    public String name;
    public String startDate;
    public String endDate;
    public Integer days;
    public Double returns;

    public ManagerHistoryBean(String id, String name, String startDate, String endDate, Integer days, Double returns) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate==null?"至今":endDate;
        this.days = days;
        this.returns = returns;
    }
}
