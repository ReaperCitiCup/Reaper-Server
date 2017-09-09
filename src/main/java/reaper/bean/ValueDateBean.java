package reaper.bean;

public class ValueDateBean {
    /**
     * 日期
     */
    public String date;

    /**
     * 净值
     */
    public Double value;

    public ValueDateBean(String date, Double value) {
        this.date = date;
        this.value = value;
    }
}
