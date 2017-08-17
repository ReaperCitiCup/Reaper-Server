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

    @Column(length = 1)
    private String gender;

    @Column(length = 32)
    private String university;

    private Date appointedDate;

    @Column(length = 65535)
    private String introduction;

    private Double totalScope;

    private Double bestReturns;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
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
}
