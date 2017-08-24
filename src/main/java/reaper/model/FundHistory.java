package reaper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public String getFundType1() {
        return fundType1;
    }

    public void setFundType1(String fundType1) {
        this.fundType1 = fundType1;
    }

    public String getFundType2() {
        return fundType2;
    }

    public void setFundType2(String fundType2) {
        this.fundType2 = fundType2;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
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

    private String managerId;
    private String fundCode;
    private String fundName;
    private String fundType1;
    private String fundType2;
    private Double size;
    private Date startDate;
    private Date endDate;
    private String time;
    private Double payback;




}
