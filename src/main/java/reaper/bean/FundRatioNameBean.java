package reaper.bean;

/**
 * 含有名字的基金组合组成
 *
 * @author keenan on 11/09/2017
 */
public class FundRatioNameBean {
    public String code;

    public String name;

    public Double weight;

    public FundRatioNameBean(String code, String name, Double weight) {
        this.code = code;
        this.name = name;
        this.weight = weight;
    }

    public FundRatioNameBean() {
    }
}
