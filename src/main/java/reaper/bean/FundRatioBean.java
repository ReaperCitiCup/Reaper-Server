package reaper.bean;

/**
 * 组合基金占比
 *
 * @author keenan on 09/09/2017
 */
public class FundRatioBean {
    /**
     * 股票号
     */
    public String id;

    /**
     * 占比
     */
    public Double ratio;

    public FundRatioBean() {
    }

    public FundRatioBean(String id, Double ratio) {
        this.id = id;
        this.ratio = ratio;
    }
}
