package reaper.util;

import reaper.bean.FundInfoBean;
import reaper.bean.MiniBean;
import reaper.bean.RateBean;
import reaper.model.Fund;
import reaper.model.FundNetValue;

import java.text.SimpleDateFormat;

public class FundModelToBean {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public FundInfoBean modelToBean(Fund fund, FundNetValue fundNetValue, RateBean rateBean, MiniBean manager,MiniBean company){
        FundInfoBean res = new FundInfoBean();
        res.code = fund.getFundCode();
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
        return res;
    }
}
