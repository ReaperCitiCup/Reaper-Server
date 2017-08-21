package reaper.model;

import javax.persistence.*;

/**
 * Created by max on 2017/8/21.
 */

@Entity
@Table(name = "manager_info")
public class ManagerInfo {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String code;

    @Column(length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 6)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ManagerInfo(Integer id) {
        this.id = id;
    }
}
