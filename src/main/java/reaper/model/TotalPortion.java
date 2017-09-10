package reaper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "total_portion")
public class TotalPortion {
    @Id
    @Column(length = 6)
    private String code;

    private Double value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TotalPortion{" +
                "code='" + code + '\'' +
                ", value=" + value +
                '}';
    }
}
