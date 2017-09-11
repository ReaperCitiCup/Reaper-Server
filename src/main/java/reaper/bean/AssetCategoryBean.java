package reaper.bean;

import java.util.List;

/**
 * 如果选择策略间分散 则 category 有效
 * 每个属性包含基金代码，分号隔开
 *
 * @author keenan on 09/09/2017
 */
public class AssetCategoryBean {
    /**
     * 股票策略
     */
    public List<String> stockStrategy;

    /**
     * 管理期货
     */
    public List<String> managedFutures;

    /**
     * 其他
     */
    public List<String> other;

    public AssetCategoryBean(List<String> stockStrategy, List<String> managedFutures, List<String> other) {
        this.stockStrategy = stockStrategy;
        this.managedFutures = managedFutures;
        this.other = other;
    }

    public AssetCategoryBean() {
    }
}
