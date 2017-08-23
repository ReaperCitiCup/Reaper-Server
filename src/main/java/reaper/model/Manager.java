package reaper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue
    private Integer id;

    private String managerId;

    private String name;





    private Date appointedDate;

    private String introduction;

    private Double totalScope;

    private Double bestReturns;

    public Manager(){

    }

    @Column(length = 8)
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Column(length = 32)
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

    @Column(length = 65535)
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
}
