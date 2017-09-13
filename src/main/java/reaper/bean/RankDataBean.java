package reaper.bean;

/**
 * Created by Feng on 2017/9/8.
 */
public class RankDataBean {
    /**
     * 类型
     */
    public String type;

    /**
     * 排名数
     */
    public Integer rank;

    /**
     * 参与排名数
     */
    public Integer total;

    public RankDataBean(String type, Integer rank, Integer total) {
        this.type = type;
        this.rank = rank;
        this.total = total;
    }
}
