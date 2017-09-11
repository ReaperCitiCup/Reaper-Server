package reaper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 8)
    private String managerId;

    @Column(length = 32)
    private String name;

    private Date appointedDate;

    @Column(length = 3000)
    private String introduction;

    private Double totalScope;

    private Double bestReturns;

    /**
     * 收益率
     */
    private Double returnRate;

    /**
     * 风险率
     */
    private Double risk;

    public Manager(){

    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAppointedDate() {
        return appointedDate;
    }

    public void setAppointedDate(Date appointedDate) {
        this.appointedDate = appointedDate;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getTotalScope() {
        return totalScope;
    }

    public void setTotalScope(Double totalScope) {
        this.totalScope = totalScope;
    }

    public Double getBestReturns() {
        return bestReturns;
    }

    public void setBestReturns(Double bestReturns) {
        this.bestReturns = bestReturns;
    }

    public Double getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(Double returnRate) {
        this.returnRate = returnRate;
    }

    public Double getRisk() {
        return risk;
    }

    public void setRisk(Double risk) {
        this.risk = risk;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId='" + managerId + '\'' +
                ", name='" + name + '\'' +
                ", appointedDate=" + appointedDate +
                ", introduction='" + introduction + '\'' +
                ", totalScope=" + totalScope +
                ", bestReturns=" + bestReturns +
                '}';
    }
}
