package reaper.bean;

import java.util.List;

/**
 * Created by Feng on 2017/9/2.
 */
public class RateTrendBean {
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
    public List<RateTrendDataBean> data;

    public RateTrendBean(String id, String name, List<RateTrendDataBean> data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }
}
