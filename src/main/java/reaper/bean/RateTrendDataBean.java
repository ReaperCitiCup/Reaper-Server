package reaper.bean;

/**
 * Created by Feng on 2017/9/2.
 */
public class RateTrendDataBean {
    /**
     * 日期
     */
    public String date;

    /**
     * 净值
     */
    public Double value;

    public RateTrendDataBean(String date, Double value) {
        this.date = date;
        this.value = value;
    }
}
