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
    public double threeMonths;
    /**
     * 近六月
     */
    public double sixMonths;
    /**
     * 近一年
     */
    public double oneYear;
    /**
     * 近三年
     */
    public double threeYears;
    /**
     *
     */
    public double sinceFounded;

    public RateBean(double[] rates) {
        oneMonth = rates[0];
        threeMonths = rates[1];
        sixMonths = rates[2];
        oneYear = rates[3];
        threeYears = rates[4];
        sinceFounded = rates[5];
    }

    public RateBean(double oneMonth, double threeMonths, double sixMonths, double oneYear, double threeYears, double sinceFounded) {
        this.oneMonth = oneMonth;
        this.threeMonths = threeMonths;
        this.sixMonths = sixMonths;
        this.oneYear = oneYear;
        this.threeYears = threeYears;
        this.sinceFounded = sinceFounded;
    }

    @Override
    public String toString() {
        return "RateBean{" +
                "oneMonth=" + oneMonth +
                ", threeMonth=" + threeMonths +
                ", sixMonths=" + sixMonths +
                ", oneYear=" + oneYear +
                ", threeYear=" + threeYears +
                ", sinceFounded=" + sinceFounded +
                '}';
    }
}
