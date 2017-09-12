package reaper.bean;

import java.util.List;

/**
 * @author keenan on 09/09/2017
 */
public class CategoryFundBean {
    /**
     * 分类的名称 如:股票型、混合型、或因子名称
     */
    public String name;

    public List<MiniBean> funds;

    public CategoryFundBean(String name, List<MiniBean> funds) {
        this.name = name;
        this.funds = funds;
    }

    public CategoryFundBean() {
    }
}
