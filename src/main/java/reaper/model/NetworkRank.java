package reaper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "network_rank")
public class NetworkRank {
    /**
     * 因子
     */
    @Id
    @Column(length = 30)
    private String factor;

    /**
     * 排名前五的五个基金代码，以|分隔
     */
    @Column(length = 50,nullable = false)
    private String codes;

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }
}
