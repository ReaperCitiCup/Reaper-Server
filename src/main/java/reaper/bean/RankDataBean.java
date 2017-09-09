package reaper.bean;

/**
 * Created by Feng on 2017/9/8.
 */
public class RankDataBean {
    /**
     * 月份
     */
    public Integer month;

    /**
     * 排名数
     */
    public Integer rank;

    /**
     * 参与排名数
     */
    public Integer total;

    public RankDataBean(Integer month, Integer rank, Integer total) {
        this.month = month;
        this.rank = rank;
        this.total = total;
    }
}
