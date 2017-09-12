package reaper.bean;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by Feng on 2017/9/10.
 */
public class FundLinkDataBean {
    public Integer source;
    public Integer target;

    /**
     * 权重
     */
    public Double value;

    public FundLinkDataBean(Integer source, Integer target, Double value) {
        this.source = source;
        this.target = target;
        this.value = value;
    }
}
