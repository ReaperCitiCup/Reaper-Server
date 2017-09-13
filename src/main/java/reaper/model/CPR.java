package reaper.model;

import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.*;

/**
 * Created by max on 2017/9/12.
 */
@Entity
@Table(name = "cpr")
public class CPR {

    @Id
    @GeneratedValue
    private Integer id;



    @Column(length = 6)
    private String fundId;

    private Integer LL;//连输期数

    private Integer WL;

    public Integer getLW() {
        return LW;
    }

    public void setLW(Integer LW) {
        this.LW = LW;
    }

    private Integer LW;

    private  Integer WW;//连赢期数

    private Double CPR;//持续性指标

    private Integer CRPProPortion;//CPR的百分位数

    private Integer total;//总期数

    public CPR() {
    }


    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public Integer getLL() {
        return LL;
    }

    public void setLL(Integer LL) {
        this.LL = LL;
    }

    public Integer getWL() {
        return WL;
    }

    public void setWL(Integer WL) {
        this.WL = WL;
    }

    public Integer getWW() {
        return WW;
    }

    public void setWW(Integer WW) {
        this.WW = WW;
    }

    public Double getCPR() {
        return CPR;
    }

    public void setCPR(Double CPR) {
        this.CPR = CPR;
    }

    public Integer getCRPProPortion() {
        return CRPProPortion;
    }

    public void setCRPProPortion(Integer CRPProPortion) {
        this.CRPProPortion = CRPProPortion;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }




}
