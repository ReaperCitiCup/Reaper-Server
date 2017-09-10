package reaper.bean;

/**
 * 1=低 2=中 3=高
 *
 * @author keenan on 09/09/2017
 */
public class AssetTargetBean {
    public int profit;

    public int risk;

    public AssetTargetBean(int profit, int risk) {
        this.profit = profit;
        this.risk = risk;
    }

    public AssetTargetBean() {
    }
}
