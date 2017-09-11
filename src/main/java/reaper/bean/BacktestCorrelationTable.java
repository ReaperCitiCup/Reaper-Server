package reaper.bean;

/**
 * @author keenan on 11/09/2017
 */
public class BacktestCorrelationTable {
    /**
     * 格式为   "000001 name"
     */
    public String fund1;
    public String fund2;
    public Double value;

    public BacktestCorrelationTable(String fund1, String fund2, Double value) {
        this.fund1 = fund1;
        this.fund2 = fund2;
        this.value = value;
    }

    public BacktestCorrelationTable() {
    }
}
