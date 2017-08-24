package reaper.bean;

/**
 * 增长率
 */
public class RateBean {
    /**
     * 近一月
     */
    public double oneMonth;
    /**
     * 近三月
     */
    public double threeMonth;
    /**
     * 近六月
     */
    public double sixMonth;
    /**
     * 近一年
     */
    public double oneYear;
    /**
     * 近三年
     */
    public double threeYear;
    /**
     *
     */
    public double sinceFounded;

    public RateBean(double[] rates) {
        oneMonth = rates[0];
        threeMonth = rates[1];
        sixMonth = rates[2];
        oneYear = rates[3];
        threeYear = rates[4];
        sinceFounded = rates[5];
    }

    public RateBean(double oneMonth, double threeMonth, double sixMonth, double oneYear, double threeYear, double sinceFounded) {
        this.oneMonth = oneMonth;
        this.threeMonth = threeMonth;
        this.sixMonth = sixMonth;
        this.oneYear = oneYear;
        this.threeYear = threeYear;
        this.sinceFounded = sinceFounded;
    }

    @Override
    public String toString() {
        return "RateBean{" +
                "oneMonth=" + oneMonth +
                ", threeMonth=" + threeMonth +
                ", sixMonth=" + sixMonth +
                ", oneYear=" + oneYear +
                ", threeYear=" + threeYear +
                ", sinceFounded=" + sinceFounded +
                '}';
    }
}
