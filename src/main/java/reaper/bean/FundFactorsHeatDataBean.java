package reaper.bean;

public class FundFactorsHeatDataBean {
    public Integer factorIndex;
    public Integer fundIndex;
    public Integer value;

    public FundFactorsHeatDataBean(Integer factorIndex, Integer fundIndex, Integer value) {
        this.factorIndex = factorIndex;
        this.fundIndex = fundIndex;
        this.value = value;
    }

    @Override
    public String toString() {
        return "FundFactorsHeatDataBean{" +
                "factorIndex=" + factorIndex +
                ", fundIndex=" + fundIndex +
                ", value=" + value +
                '}';
    }
}
