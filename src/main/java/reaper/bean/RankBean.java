package reaper.bean;

import java.util.List;

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
     * 基金排名信息
     */
    public List<RankDataBean> data;

    public RankBean(String id, String name, List<RankDataBean> data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }
}
