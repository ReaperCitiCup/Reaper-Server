package reaper.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.bean.*;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Feng on 2017/9/5.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class FundServiceTest {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    FundService fundService;

    @Test
    public void findFundByKeywordTest() throws Exception {
        reaper.util.Page<FundMiniBean> fundMiniBeanPage=
        fundService.findFundByKeyword("000005", "code", 5, 3);
        System.out.println();
    }

    //当fund存在时
    @Test
    public void findFundByCodeTest1() throws Exception {
        FundBean fundBean=fundService.findFundByCode("000005");
        assertArrayEquals(
                new String[]{
                        "000005", "嘉实增强信用定期债券", "定开债券", "中低风险", "2013-03-08",
                        "1", "30198173", "刘宁", "80000223", "嘉实"
                },
                new String[]{
                        fundBean.code, fundBean.name, fundBean.type[0], fundBean.type[1],fundBean.establishmentDate,
                        fundBean.manager.size()+"", fundBean.manager.get(0).code, fundBean.manager.get(0).name, fundBean.company.code, fundBean.company.name
                }
                );
        assertArrayEquals(
                new Double[]{
                        10.13, 1.02,1.203, 0.1, 0.2, 2.0, 2.18, 1.28, 17.22, 19.92
                },
                new Double[]{
                        fundBean.scope,fundBean.unitNetValue,fundBean.cumulativeNetValue, fundBean.dailyRate, fundBean.rate.oneMonth,
                        fundBean.rate.threeMonths, fundBean.rate.sixMonths, fundBean.rate.oneYear, fundBean.rate.threeYears, fundBean.rate.sinceFounded
                }
        );
    }

    //当fund不存在时
    @Test
    public void findFundByCodeTest2() throws Exception{
        assertArrayEquals(
                new FundBean[]{
                        null, null
                },
                new FundBean[]{
                        fundService.findFundByCode("0010000"),
                        fundService.findFundByCode("0001")
                }
        );
    }

    //当fund为null时
    @Test
    public void findFundByCodeTest3() throws Exception{
        Assert.assertEquals(null, fundService.findFundByCode(null));
    }

    @Test
    public void findUnitNetValueTrendByCodeTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000005");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.02, valueDateBeans.get(1091).value.doubleValue());
    }

    @Test
    public void findUnitNetValueTrendByCodeTest2() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000006");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findUnitNetValueTrendByCodeTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000007");
        Assert.assertEquals(1060, valueDateBeans.size());
        Assert.assertEquals("2013-03-15", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-21", valueDateBeans.get(1059).date);
        Assert.assertEquals(1.1382, valueDateBeans.get(1059).value.doubleValue());
    }

    @Test
    public void findUnitNetValueTrendByCodeTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000801");
        Assert.assertEquals(682, valueDateBeans.size());
        Assert.assertEquals("2014-11-04", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-22", valueDateBeans.get(681).date);
        Assert.assertEquals(1.123, valueDateBeans.get(681).value.doubleValue());
    }

    @Test
    public void findUnitNetValueTrendByCodeTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000948");
        Assert.assertEquals(598, valueDateBeans.size());
        Assert.assertEquals("2015-01-13", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-09-01", valueDateBeans.get(597).date);
        Assert.assertEquals(1.1944, valueDateBeans.get(597).value.doubleValue());
    }

    @Test
    public void findUnitNetValueTrendByCodeTest6() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000950");
        Assert.assertEquals(637, valueDateBeans.size());
        Assert.assertEquals("2015-01-22", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-09-01", valueDateBeans.get(636).date);
        Assert.assertEquals(0.927, valueDateBeans.get(636).value.doubleValue());
    }

    @Test
    public void findUnitNetValueTrendByCodeTest7() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("5");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.02, valueDateBeans.get(1091).value.doubleValue());
    }

    @Test
    public void findUnitNetValueTrendByCodeTest8() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("005");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.02, valueDateBeans.get(1091).value.doubleValue());
    }

    @Test
    public void findUnitNetValueTrendByCodeTest9() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("0");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findUnitNetValueTrendByCodeTest10() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode(null);
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("000005");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.203, valueDateBeans.get(1091).value.doubleValue());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest2() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("000006");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("000007");
        Assert.assertEquals(1060, valueDateBeans.size());
        Assert.assertEquals("2013-03-15", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-21", valueDateBeans.get(1059).date);
        Assert.assertEquals(1.147, valueDateBeans.get(1059).value.doubleValue());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("000801");
        Assert.assertEquals(682, valueDateBeans.size());
        Assert.assertEquals("2014-11-04", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-22", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.123, valueDateBeans.get(1091).value.doubleValue());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("000948");
        Assert.assertEquals(598, valueDateBeans.size());
        Assert.assertEquals("2015-01-13", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-09-01", valueDateBeans.get(597).date);
        Assert.assertEquals(1.1944, valueDateBeans.get(597).value.doubleValue());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest6() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("000950");
        Assert.assertEquals(637, valueDateBeans.size());
        Assert.assertEquals("2015-01-22", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-09-01", valueDateBeans.get(636).date);
        Assert.assertEquals(0.927, valueDateBeans.get(636).value.doubleValue());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest7() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("5");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.203, valueDateBeans.get(1091).value.doubleValue());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest8() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("005");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.203, valueDateBeans.get(1091).value.doubleValue());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest9() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("0");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest10() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode(null);
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "1");
        Assert.assertEquals(13, valueDateBeans.size());
        Assert.assertEquals("2017-08-09", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(12).date);
        Assert.assertEquals(0.30000000000000004, valueDateBeans.get(12).value.doubleValue());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest2() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "3");
        Assert.assertEquals(56, valueDateBeans.size());
        Assert.assertEquals("2017-06-09", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(55).date);
        Assert.assertEquals(1.9000000000000004, valueDateBeans.get(55).value.doubleValue());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "6");
        Assert.assertEquals(117, valueDateBeans.size());
        Assert.assertEquals("2017-03-09", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(116).date);
        Assert.assertEquals(2.1800000000000006, valueDateBeans.get(116).value.doubleValue());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "12");
        Assert.assertEquals(234, valueDateBeans.size());
        Assert.assertEquals("2016-09-09", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(233).date);
        Assert.assertEquals(1.28, valueDateBeans.get(233).value.doubleValue());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "36");
        Assert.assertEquals(725, valueDateBeans.size());
        Assert.assertEquals("2014-09-09", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(724).date);
        Assert.assertEquals(17.219999999999978, valueDateBeans.get(724).value.doubleValue());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest6() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "all");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(19.92000000000005, valueDateBeans.get(1091).value.doubleValue());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest7() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("5", "6");
        Assert.assertEquals(117, valueDateBeans.size());
        Assert.assertEquals("2017-03-09", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(116).date);
        Assert.assertEquals(2.1800000000000006, valueDateBeans.get(116).value.doubleValue());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest8() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("005", "6");
        Assert.assertEquals(117, valueDateBeans.size());
        Assert.assertEquals("2017-03-09", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2017-08-25", valueDateBeans.get(116).date);
        Assert.assertEquals(2.1800000000000006, valueDateBeans.get(116).value.doubleValue());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest9() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("0", "6");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest10() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode(null, "6");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest11() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", null);
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest12() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode(null, null);
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest13() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("005", null);
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest14() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000006", null);
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest15() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000006", "6");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    //TODO
    @Test
    public void findCurrentAssetByCodeTest() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode("000005");
//        assertArrayEquals(
//                new Double[]{
//                        1.0, 2.0 ,3.0
//                },
//                new Double[]{
//                        currentAssetBean.bond, currentAssetBean.stock, currentAssetBean.bank
//                }
//        );
    }

    //TODO
    @Test
    public void findHistoryManagerByCodeTest() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("000005");
        Assert.assertEquals(2, managerHistoryBeans.size());
        ManagerHistoryBean managerHistoryBean1=managerHistoryBeans.get(0);
        assertArrayEquals(
                new String[]{
                        "30198173", "刘宁", "2013-03-08", null, "186", "21.63"
                },
                new String[]{
                        managerHistoryBean1.id, managerHistoryBean1.name, managerHistoryBean1.startDate, managerHistoryBean1.endDate, managerHistoryBean1.days.toString(), managerHistoryBean1.returns.toString()
                }
        );
        ManagerHistoryBean managerHistoryBean2=managerHistoryBeans.get(1);
        assertArrayEquals(
                new String[]{
                        "30138351", "曲扬", "2016-07-11", "2016-12-02", "144", "0.36"
                },
                new String[]{
                        managerHistoryBean2.id, managerHistoryBean2.name, managerHistoryBean2.startDate, managerHistoryBean2.endDate, managerHistoryBean2.days.toString(), managerHistoryBean2.returns.toString()
                }
        );
    }

    @Test
    public void findJensenByCodeTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000005");
        Assert.assertEquals(931, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.000290714438132, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(930).date);
        Assert.assertEquals(-0.00226152126828, valueDateBeans.get(930).value.doubleValue());
    }

    @Test
    public void findJensenByCodeTest2() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000006");
        Assert.assertEquals(0, valueDateBeans.size());
    }
    @Test
    public void findJensenByCodeTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000007");
        Assert.assertEquals(903, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.00154304995858, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2013-03-15", valueDateBeans.get(902).date);
        Assert.assertEquals(-0.00256104150246, valueDateBeans.get(902).value.doubleValue());
    }

    @Test
    public void findJensenByCodeTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000801");
        Assert.assertEquals(525, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.00125179956437, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2014-11-04", valueDateBeans.get(524).date);
        Assert.assertEquals(-0.00234609226032, valueDateBeans.get(524).value.doubleValue());
    }

    @Test
    public void findJensenByCodeTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000948");
        Assert.assertEquals(442, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(0.00617736166631, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2015-01-13", valueDateBeans.get(441).date);
        Assert.assertEquals(-0.00160075045976, valueDateBeans.get(441).value.doubleValue());
    }

    @Test
    public void findJensenByCodeTest6() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000950");
        Assert.assertEquals(472, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(0.00450910674501, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(471).date);
        Assert.assertEquals(-0.00509896744982, valueDateBeans.get(471).value.doubleValue());
    }

    @Test
    public void findJensenByCodeTest7() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("5");
        Assert.assertEquals(931, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.000290714438132, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(930).date);
        Assert.assertEquals(-0.00226152126828, valueDateBeans.get(930).value.doubleValue());
    }

    @Test
    public void findJensenByCodeTest8() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("005");
        Assert.assertEquals(931, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.000290714438132, valueDateBeans.get(0).value.doubleValue());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(930).date);
        Assert.assertEquals(-0.00226152126828, valueDateBeans.get(930).value.doubleValue());
    }

    @Test
    public void findJensenByCodeTest9() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("0");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findJensenByCodeTest10() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode(null);
        Assert.assertEquals(0, valueDateBeans.size());
    }
}
