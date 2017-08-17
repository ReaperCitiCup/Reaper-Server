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
}
