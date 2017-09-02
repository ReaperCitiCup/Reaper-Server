package reaper.bean;

/**
 * Created by Feng on 2017/9/2.
 */
public class RankTrendDataBean {
    /**
     * 日期
     */
    public String date;

    /**
     * 排名数
     */
    public Integer rank;

    /**
     * 参与排名数
     */
    public Integer total;

    public RankTrendDataBean(String date, Integer rank, Integer total) {
        this.date = date;
        this.rank = rank;
        this.total = total;
    }
}
