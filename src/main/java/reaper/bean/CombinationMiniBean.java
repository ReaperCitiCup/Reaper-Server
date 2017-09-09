package reaper.bean;

/**
 * @author keenan on 09/09/2017
 */
public class CombinationMiniBean {
    /**
     * 组合id
     */
    public String id;

    /**
     * 组合名字
     */
    public String name;

    /**
     * 累计收益
     */
    public double cumulativeProfit;

    /**
     * 年化收益
     */
    public double annualProfit;

    /**
     * 最大回撤
     */
    public double maxRetracement;

    public CombinationMiniBean(String id, String name, double cumulativeProfit, double annualProfit, double maxRetracement) {
        this.id = id;
        this.name = name;
        this.cumulativeProfit = cumulativeProfit;
        this.annualProfit = annualProfit;
        this.maxRetracement = maxRetracement;
    }
}
