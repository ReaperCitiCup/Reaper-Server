package reaper.bean;

import java.util.List;

public class FundFactorsHeatBean {
    public List<String> funds;
    public List<String> factors;
    public List<FundFactorsHeatDataBean> datas;

    public FundFactorsHeatBean(List<String> funds, List<String> factors, List<FundFactorsHeatDataBean> datas) {
        this.funds = funds;
        this.factors = factors;
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "FundFactorsHeatBean{" +
                "funds=" + funds +
                ", factors=" + factors +
                ", datas=" + datas +
                '}';
    }
}
