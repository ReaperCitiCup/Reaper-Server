package reaper.bean;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by Feng on 2017/9/10.
 */
public class LinkDataBean {
    public Integer source;
    public Integer target;

    /**
     * 权重
     */
    public Integer value;

    public LinkDataBean(Integer source, Integer target, Integer value) {
        this.source = source;
        this.target = target;
        this.value = value;
    }
}
