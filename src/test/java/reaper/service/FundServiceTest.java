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
        System.out.println();
    }

    @Test
    public void findUnitNetValueTrendByCodeTest() throws Exception {

    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest() throws Exception {

    }

    @Test
    public void findCumulativeRateTrendByCodeTest() throws Exception {

    }

    @Test
    public void findHistoryManagersByCodeTest() throws Exception {

    }

    @Test
    public void findCurrentAssetByCodeTest() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode("000005");
        assertArrayEquals(
                new Double[]{
                        1.0, 2.0 ,3.0
                },
                new Double[]{
                        currentAssetBean.bond, currentAssetBean.stock, currentAssetBean.bank
                }
        );
    }

    @Test
    public void findHistoryManagerByCodeTest() throws Exception {

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
