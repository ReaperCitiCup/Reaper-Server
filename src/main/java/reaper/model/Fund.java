package reaper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fund")
public class Fund {
    @Id
    @GeneratedValue
    private Integer id;

    private String fundCode;

    private String name;

    private String type1;

    private String type2;

    private Date establishmentDate;

    private Double scope;

    public Fund(){

    }

    @Column(length = 6)
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Column(length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(length = 32)
    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    @Column(length = 20)
    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public Double getScope() {
        return scope;
    }

    public void setScope(Double scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fundCode='" + fundCode + '\'' +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", establishmentDate=" + establishmentDate +
                ", scope=" + scope +
                '}';
    }
}
