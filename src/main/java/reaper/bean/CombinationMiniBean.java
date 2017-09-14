package reaper.bean;

/**
 * @author keenan on 09/09/2017
 */
public class CombinationMiniBean {
    /**
     * 组合id
     */
    public Integer id;

    /**
     * 组合名字
     */
    public String name;

    /**
     * 最新收益率
     */
    public Double newProfit;

    /**
     * 年化收益
     */
    public Double annualProfit;

    /**
     * 平均相关系数
     */
    public Double correlationCoefficient;

    public CombinationMiniBean(Integer id, String name, Double newProfit, Double annualProfit, Double correlationCoefficient) {
        this.id = id;
        this.name = name;
        this.newProfit = newProfit;
        this.annualProfit = annualProfit;
        this.correlationCoefficient = correlationCoefficient;
    }

    public CombinationMiniBean() {
    }
}
