package reaper.model;

import javax.persistence.*;

/**
 * Created by max on 2017/8/21.
 */

@Entity
@Table(name = "fund_short_message")
public class FundShortMessage {

    @Id
    @GeneratedValue
    private Integer id;
    private String code;

    public FundShortMessage() {
    }

    @Column(length = 6)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;






}
