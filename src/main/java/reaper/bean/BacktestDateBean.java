package reaper.bean;

/**
 * @author keenan on 09/09/2017
 */
public class BacktestDateBean {
    public String startDate;

    public String endDate;

    public BacktestDateBean(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BacktestDateBean() {
    }
}
