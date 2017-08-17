package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 8)
    private String companyId;

    @Column(length = 64)
    private String name;

    //TODO 其他字段待补充

    public Company(){

    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
