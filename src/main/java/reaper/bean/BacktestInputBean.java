package reaper.bean;

/**
 * @author keenan on 09/09/2017
 */
public class BacktestInputBean {
    /**
     * szzz, sz180, sz50, hs300, zz500
     */
    public String baseIndex;

    public String startDate;

    public String endDate;

    public BacktestInputBean(String baseIndex, String startDate, String endDate) {
        this.baseIndex = baseIndex;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BacktestInputBean() {
    }
}
