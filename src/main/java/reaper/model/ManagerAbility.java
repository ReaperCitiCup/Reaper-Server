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
     * 稳定性
     */
    private Double stability;

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

    public Double getStability() {
        return stability;
    }

    public void setStability(Double stability) {
        this.stability = stability;
    }

    public Double getAntirisk() {
        return antirisk;
    }

    public void setAntirisk(Double antirisk) {
        this.antirisk = antirisk;
    }
}
