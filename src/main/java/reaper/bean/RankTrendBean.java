package reaper.bean;

import java.util.List;

/**
 * Created by Feng on 2017/9/2.
 */
public class RankTrendBean {
    /**
     * 基金代码
     */
    public String id;

    /**
     * 基金名称
     */
    public String name;

    /**
     * 基金数据
     */
    public List<RankTrendDataBean> data;

    public RankTrendBean(String id, String name, List<RankTrendDataBean> data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }
}
