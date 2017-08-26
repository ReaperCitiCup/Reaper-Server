package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "manager_company")
public class ManagerCompany {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 8)
    private String managerId;

    @Column(length = 8)
    private String companyId;

    public ManagerCompany(){

    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
