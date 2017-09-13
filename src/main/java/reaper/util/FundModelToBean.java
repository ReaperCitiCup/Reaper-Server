package reaper.util;

import reaper.bean.FundBean;
import reaper.bean.IdNameBean;
import reaper.bean.MiniBean;
import reaper.bean.RateBean;
import reaper.model.Fund;
import reaper.model.FundNetValue;

import java.text.SimpleDateFormat;
import java.util.List;

public class FundModelToBean {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public FundBean modelToBean(Fund fund, FundNetValue fundNetValue, RateBean rateBean, List<IdNameBean> manager, IdNameBean company){
        FundBean res = new FundBean();
        res.code = fund.getCode();
        res.name = fund.getName();
        String types[] = {fund.getType1(),fund.getType2()};
        res.type = types;
        res.establishmentDate = sdf.format(fund.getEstablishmentDate());
        res.scope = fund.getScope();
        res.unitNetValue = fundNetValue.getUnitNetValue();
        res.cumulativeNetValue = fundNetValue.getCumulativeNetValue();
        res.dailyRate = fundNetValue.getDailyRate();
        res.rate = rateBean;
        res.manager = manager;
        res.company = company;
        List<Double> assesses = RightNowMessage.getSingleFundRightNowMessage(fund.getCode());
        res.assessNetValue = assesses.get(0);
        res.assessDailyRate = assesses.get(2);
        res.assessIncrease = res.assessNetValue*res.assessDailyRate;
        return res;
    }
}
