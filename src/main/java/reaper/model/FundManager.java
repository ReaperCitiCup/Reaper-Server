package reaper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fund_manager")
public class FundManager {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String fundId;

    @Column(length = 8)
    private String managerId;

    private Date startDate;

    private Date endDate;

    public FundManager(){

    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
