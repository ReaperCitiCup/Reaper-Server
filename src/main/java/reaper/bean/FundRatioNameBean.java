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

    /**
     * 持仓比率
     */
    public Double positionRatio;

    public FundRatioNameBean(String code, String name, Double weight, Double positionRatio) {
        this.code = code;
        this.name = name;
        this.weight = weight;
        this.positionRatio = positionRatio;
    }

    public FundRatioNameBean() {
    }
}
