package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "manager_company")
public class ManagerCompany {
    @Id
    @GeneratedValue
    private Integer id;

    private String managerId;

    private String companyId;

    public ManagerCompany(){

    }

    @Column(length = 8)
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Column(length = 8)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
