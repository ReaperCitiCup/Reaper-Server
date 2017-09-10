package reaper.bean;

import java.util.List;

/**
 * @author keenan on 09/09/2017
 */
public class CategoryFundBean {
    /**
     * 股票型
     */
    public List<MiniBean> stock;

    /**
     * 债券型
     */
    public List<MiniBean> bond;

    /**
     * 混合型
     */
    public List<MiniBean> hybrid;

    public CategoryFundBean() {
    }

    public CategoryFundBean(List<MiniBean> stock, List<MiniBean> bond, List<MiniBean> hybrid) {
        this.stock = stock;
        this.bond = bond;
        this.hybrid = hybrid;
    }
}
