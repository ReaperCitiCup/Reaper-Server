package reaper.service.fundServiceTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reaper.bean.FundBean;
import reaper.bean.MiniBean;
import reaper.bean.RateBean;
import reaper.service.FundService;
import reaper.service.impl.FundServiceImpl;
import reaper.util.FundModelToBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Feng on 2017/9/7.
 */

@RunWith(Parameterized.class)
@SpringBootTest()
public class TestFindFundByCode {
//    @Autowired
    FundService fundService=new FundServiceImpl();

    private FundBean expected;
    private String code;

    public static FundBean toFundBean(String code, String name, String[] type, String establishmentDate, double scope,
                               double unitNetValue, double cumulativeNetValue, double dailyRate, RateBean rate,
                               List<MiniBean> manager, MiniBean company){
        FundBean fundBean=new FundBean();
        fundBean.code=code;
        fundBean.name=name;
        fundBean.type=type;
        fundBean.establishmentDate=establishmentDate;
        fundBean.scope=scope;
        fundBean.unitNetValue=unitNetValue;
        fundBean.cumulativeNetValue=cumulativeNetValue;
        fundBean.dailyRate=dailyRate;
        fundBean.rate=rate;
        fundBean.manager=manager;
        fundBean.company=company;
        return fundBean;
    }

    @Parameterized.Parameters
    public static Collection words(){
        List<MiniBean> min;
        String[][] types={
                {"定开债券", "中低风险"}
        };
        return Arrays.asList(new Object[][]{
                {toFundBean("000005","嘉实增强信用定期债券",types[0], "2013-03-08", 10.13, 1.02, 1.203,
                        0.1, new RateBean(0.3, 2, 2.18, 1.28, 17.22, 19.92), min=new ArrayList<MiniBean>(){{add(new MiniBean("30198173", "刘宁"));}}, new MiniBean("80000223", "嘉实")),
                "000005"}

        });
    }

    public TestFindFundByCode(FundBean expected, String code){
        this.expected=expected;
        this.code=code;
    }

    @Test
    public void findFundByCodeTest(){
        Assert.assertEquals(expected, fundService.findFundByCode(code));
    }
}
