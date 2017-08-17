package reaper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fund")
public class Fund {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String fundId;

    @Column(length = 128)
    private String name;

    @Column(length = 32)
    private String shortname;

    @Column(length = 32)
    private String type;

    private Date establishmentDate;

    private Double scope;

    public Fund(){

    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
