package reaper.bean;

import reaper.util.FormatData;

public class FundFactorsHeatDataBean {
    public Integer factorIndex;
    public Integer fundIndex;
    public Double value;

    public FundFactorsHeatDataBean(Integer factorIndex, Integer fundIndex, Double value) {
        this.factorIndex = factorIndex;
        this.fundIndex = fundIndex;
        this.value = FormatData.fixToFive(value);
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
