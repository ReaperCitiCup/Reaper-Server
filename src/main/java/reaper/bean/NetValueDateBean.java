package reaper.bean;

public class NetValueDateBean {
    /**
     * 日期
     */
    public String date;

    /**
     * 净值
     */
    public Double value;

    public NetValueDateBean(String date, Double value) {
        this.date = date;
        this.value = value;
    }
}
