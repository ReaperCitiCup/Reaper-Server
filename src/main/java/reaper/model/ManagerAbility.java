package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "manager_ability")
public class ManagerAbility {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 经理id
     */
    @Column(length = 8)
    private String managerId;

    /**
     * 经验值
     */
    private Double experience;

    /**
     * 择时能力
     */
    private Double timing;

    /**
     * 收益率
     */
    private Double returns;

    /**
     * 择股能力
     */
    private Double stockSelect;

    /**
     * 抗风险
     */
    private Double antirisk;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Double getTiming() {
        return timing;
    }

    public void setTiming(Double timing) {
        this.timing = timing;
    }

    public Double getReturns() {
        return returns;
    }

    public void setReturns(Double returns) {
        this.returns = returns;
    }

    public Double getStockSelect() {
        return stockSelect;
    }

    public void setStockSelect(Double stockSelect) {
        this.stockSelect = stockSelect;
    }

    public Double getAntirisk() {
        return antirisk;
    }

    public void setAntirisk(Double antirisk) {
        this.antirisk = antirisk;
    }
}
