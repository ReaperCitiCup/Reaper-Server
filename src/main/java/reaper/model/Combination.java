package reaper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author keenan on 10/09/2017
 */
@Entity
@Table(name = "combination")
public class Combination {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 风险和收益 1-10
     */
    private Integer risk_profit;

    /**
     * 用户名
     */
    private Integer userid;

    /**
     * 组合名
     */
    private String name;

    /**
     * 基金代码(split by '|')
     */
    private String funds;

    /**
     * 权重(split by '|')
     */
    private String weights;

    public Combination() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRisk_profit() {
        return risk_profit;
    }

    public void setRisk_profit(Integer risk_profit) {
        this.risk_profit = risk_profit;
    }

    public Combination(Integer risk_profit, Integer userid, String name, String funds, String weights) {
        this.risk_profit = risk_profit;
        this.userid = userid;
        this.name = name;
        this.funds = funds;
        this.weights = weights;
    }

    @Override
    public String toString() {
        return "Combination{" +
                "id=" + id +
                ", risk_profit=" + risk_profit +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", funds='" + funds + '\'' +
                ", weights='" + weights + '\'' +
                '}';
    }
}
