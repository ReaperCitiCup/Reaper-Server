package reaper.bean;

/**
 * Created by Feng on 2017/9/2.
 */
public class RankBean {
    /**
     * 基金代码
     */
    public String id;

    /**
     * 基金名称
     */
    public String name;

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

    public RankBean(String id, String name, Integer month, Integer rank, Integer total) {
        this.id = id;
        this.name = name;
        this.month = month;
        this.rank = rank;
        this.total = total;
    }
}
