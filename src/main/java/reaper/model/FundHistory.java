package reaper.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by max on 2017/8/22.
 */
@Entity
@Table(name = "fund_history")
public class FundHistory {
    @Id
    @GeneratedValue
    private Integer id;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPayback() {
        return payback;
    }

    public void setPayback(Double payback) {
        this.payback = payback;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Column(length = 8)
    private String managerId;
    @Column(length = 6)
    private String fundCode;
    private String fundName;
    private Date startDate;
    private Date endDate;
    @Column(length = 64)
    private String time;
    private Double payback;
    @Column(length = 32)
    private String managerName;


    @Override
    public String toString() {
        return "FundHistory{" +
                "managerId='" + managerId + '\'' +
                ", fundCode='" + fundCode + '\'' +
                ", fundName='" + fundName+ '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", time='" + time + '\'' +
                ", payback=" + payback +
                '}';
    }
}
