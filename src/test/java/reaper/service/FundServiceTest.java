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

    //TODO
    @Test
    public void findFundByKeywordTest() throws Exception {
        reaper.util.Page<FundMiniBean> fundMiniBeanPage=
        fundService.findFundByKeyword("000005", "code", 5, 3);
        System.out.println();
    }

    @Test
    public void findFundNameByCodeTest1() throws Exception {
        MiniBean miniBean=fundService.findFundNameByCode("000005");
        assertArrayEquals(
                new String[]{
                        "000005", "嘉实增强信用定期债券"
                },
                new String[]{
                        miniBean.code, miniBean.name
                }
        );
    }

    @Test
    public void findFundNameByCodeTest2() throws Exception {
        MiniBean miniBean=fundService.findFundNameByCode("000006");
        Assert.assertNull(miniBean);
    }

    @Test
    public void findFundNameByCodeTest3() throws Exception {
        MiniBean miniBean=fundService.findFundNameByCode("000007");
        assertArrayEquals(
                new String[]{
                        "000007", "鹏华国企债债券"
                },
                new String[]{
                        miniBean.code, miniBean.name
                }
        );
    }

    @Test
    public void findFundNameByCodeTest4() throws Exception {
        MiniBean miniBean=fundService.findFundNameByCode("000950");
        assertArrayEquals(
                new String[]{
                        "000950", "易方达沪深300非银ETF联接"
                },
                new String[]{
                        miniBean.code, miniBean.name
                }
        );
    }

    @Test
    public void findFundNameByCodeTest5() throws Exception {
        MiniBean miniBean=fundService.findFundNameByCode("5");
        assertArrayEquals(
                new String[]{
                        "000005", "嘉实增强信用定期债券"
                },
                new String[]{
                        miniBean.code, miniBean.name
                }
        );
    }

    @Test
    public void findFundNameByCodeTest6() throws Exception {
        MiniBean miniBean=fundService.findFundNameByCode("005");
        assertArrayEquals(
                new String[]{
                        "000005", "嘉实增强信用定期债券"
                },
                new String[]{
                        miniBean.code, miniBean.name
                }
        );
    }

    @Test
    public void findFundNameByCodeTest7() throws Exception {
        MiniBean miniBean=fundService.findFundNameByCode("0000005");
        Assert.assertNull(miniBean);
    }

    @Test
    public void findFundNameByCodeTest8() throws Exception {
        MiniBean miniBean=fundService.findFundNameByCode("0");
        Assert.assertNull(miniBean);
    }

    @Test
    public void findFundNameByCodeTest9() throws Exception {
        MiniBean miniBean=fundService.findFundNameByCode(null);
        Assert.assertNull(miniBean);
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

    @Test
    public void findHistoryManagerByCodeTest1() throws Exception {
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
    public void findHistoryManagerByCodeTest2() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("000006");
        Assert.assertEquals(0, managerHistoryBeans.size());
    }

    @Test
    public void findHistoryManagerByCodeTest3() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("000948");
        Assert.assertEquals(2, managerHistoryBeans.size());
        ManagerHistoryBean managerHistoryBean1=managerHistoryBeans.get(0);
        assertArrayEquals(
                new String[]{
                        "30050861", "张弘弢", "2015-01-13", null, "240", "19.44"
                },
                new String[]{
                        managerHistoryBean1.id, managerHistoryBean1.name, managerHistoryBean1.startDate, managerHistoryBean1.endDate, managerHistoryBean1.days.toString(), managerHistoryBean1.returns.toString()
                }
        );
        ManagerHistoryBean managerHistoryBean2=managerHistoryBeans.get(1);
        assertArrayEquals(
                new String[]{
                        "30106590", "徐猛", "2015-03-12", null, "182", "21.01"
                },
                new String[]{
                        managerHistoryBean2.id, managerHistoryBean2.name, managerHistoryBean2.startDate, managerHistoryBean2.endDate, managerHistoryBean2.days.toString(), managerHistoryBean2.returns.toString()
                }
        );
    }

    @Test
    public void findHistoryManagerByCodeTest4() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("000950");
        Assert.assertEquals(1, managerHistoryBeans.size());
        ManagerHistoryBean managerHistoryBean=managerHistoryBeans.get(0);
        assertArrayEquals(
                new String[]{
                        "30066753", "余海燕", "2015-01-22", null, "231", "-7.3"
                },
                new String[]{
                        managerHistoryBean.id, managerHistoryBean.name, managerHistoryBean.startDate, managerHistoryBean.endDate, managerHistoryBean.days.toString(), managerHistoryBean.returns.toString()
                }
        );
    }

    @Test
    public void findHistoryManagerByCodeTest5() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("5");
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
    public void findHistoryManagerByCodeTest6() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("005");
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
    public void findHistoryManagerByCodeTest7() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("0");
        Assert.assertEquals(0, managerHistoryBeans.size());
    }

    @Test
    public void findHistoryManagerByCodeTest8() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode(null);
        Assert.assertEquals(0, managerHistoryBeans.size());
    }

    @Test
    public void findJensenByCodeTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000948");
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

    @Test
    public void findFundCompanyByCodeTest1() throws Exception {
        MiniBean miniBean=fundService.findFundCompanyByCode("000005");
        Assert.assertEquals("80000223", miniBean.code);
        Assert.assertEquals("嘉实", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest2() throws Exception {
        MiniBean miniBean=fundService.findFundCompanyByCode("000006");
        Assert.assertNull(miniBean);
    }

    @Test
    public void findFundCompanyByCodeTest3() throws Exception {
        MiniBean miniBean=fundService.findFundCompanyByCode("000007");
        Assert.assertEquals("80000230", miniBean.code);
        Assert.assertEquals("鹏华", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest4() throws Exception {
        MiniBean miniBean=fundService.findFundCompanyByCode("000948");
        Assert.assertEquals("80000222", miniBean.code);
        Assert.assertEquals("华夏", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest5() throws Exception {
        MiniBean miniBean=fundService.findFundCompanyByCode("000950");
        Assert.assertEquals("80000229", miniBean.code);
        Assert.assertEquals("易方达", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest6() throws Exception {
        MiniBean miniBean=fundService.findFundCompanyByCode("5");
        Assert.assertEquals("80000223", miniBean.code);
        Assert.assertEquals("嘉实", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest7() throws Exception {
        MiniBean miniBean=fundService.findFundCompanyByCode("005");
        Assert.assertEquals("80000223", miniBean.code);
        Assert.assertEquals("嘉实", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest8() throws Exception {
        MiniBean miniBean=fundService.findFundCompanyByCode("0");
        Assert.assertNull(miniBean);
    }

    @Test
    public void findFundCompanyByCodeTest9() throws Exception {
        MiniBean miniBean=fundService.findFundCompanyByCode(null);
        Assert.assertNull(miniBean);
    }

    @Test
    public void findIndustryAttributionProfitTest1() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit("000005");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "0.0144071"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionProfitTest2() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit("000006");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findIndustryAttributionProfitTest3() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit("000007");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "0.1701597"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionProfitTest4() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit("000950");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "0.0116296"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionProfitTest5() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit("5");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "0.0144071"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionProfitTest6() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit("005");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "0.0144071"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionProfitTest7() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit("0");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findIndustryAttributionProfitTest8() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit("0000005");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findIndustryAttributionProfitTest9() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit(null);
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findIndustryAttributionRiskTest1() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk("000005");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "-0.0116994"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionRiskTest2() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk("000006");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findIndustryAttributionRiskTest3() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk("000007");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "-0.11921745999999998"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionRiskTest4() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk("000950");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "-0.168855"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionRiskTest5() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk("5");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "-0.0116994"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionRiskTest6() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk("005");
        Assert.assertEquals(30, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "行业因子合计", "-0.0116994"
                },
                new String[]{
                        fieldValueBeans.get(29).field, fieldValueBeans.get(29).value.toString()
                }
        );
    }

    @Test
    public void findIndustryAttributionRiskTest7() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk("0");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findIndustryAttributionRiskTest8() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk("0000005");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findIndustryAttributionRiskTest9() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk(null);
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findStyleAttributionProfitTest1() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit("000005");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.00135313, -0.000126082, 0.00000548214, 0.000438674, -0.000167033,
                        -0.000289582, 0.000219128, -0.00085754, -0.000230756, -0.00085754
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionProfitTest2() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit("000006");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findStyleAttributionProfitTest3() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit("000007");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.00680164, 0.0011665, -0.0000030105, 0.0038812, -0.000971991,
                        -0.0145181, 0.00569287, 0.0184638, 0.00656989, 0.0184638
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionProfitTest4() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit("000950");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.000330407, 0.000379858, 0.00111042, -0.000523612, -0.00000181085,
                        0.0010156, -0.000167934, 0.0038418, 0.000420719, 0.0038418
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionProfitTest5() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit("5");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.00135313, -0.000126082, 0.00000548214, 0.000438674, -0.000167033,
                        -0.000289582, 0.000219128, -0.00085754, -0.000230756, -0.00085754
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionProfitTest6() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit("005");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.00135313, -0.000126082, 0.00000548214, 0.000438674, -0.000167033,
                        -0.000289582, 0.000219128, -0.00085754, -0.000230756, -0.00085754
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionProfitTest7() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit("0000005");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findStyleAttributionProfitTest8() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit("0");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findStyleAttributionProfitTest9() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit(null);
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findStyleAttributionRiskTest1() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk("000005");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.00556793, -0.000559648, 0.0019378, 0.000410749, -0.00332839,
                        0.00711846, -0.012186, -0.00160753, 0.00795333, -0.00160753
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionRiskTest2() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk("000006");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findStyleAttributionRiskTest3() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk("000007");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.00648608, 0.00105316, 0.00709018, 0.000891195, -0.00393044,
                        0.0800572, -0.0734779, 0.00816473, -0.0631256, 0.00816473
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionRiskTest4() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk("000950");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        -0.012655, 0.00164214, -0.0170877, 0.000359096, 0.00119224,
                        -0.0316193, -0.0112253, -0.00717504, 0.0507355, -0.00717504
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionRiskTest5() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk("5");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.00556793, -0.000559648, 0.0019378, 0.000410749, -0.00332839,
                        0.00711846, -0.012186, -0.00160753, 0.00795333, -0.00160753
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionRiskTest6() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk("005");
        Assert.assertEquals(10, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "beta", "价值", "盈利能力", "成长性", "杠杆率",
                        "流动性", "动量", "非线性市值", "波动率", "市值"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field, fieldValueBeans.get(9).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.00556793, -0.000559648, 0.0019378, 0.000410749, -0.00332839,
                        0.00711846, -0.012186, -0.00160753, 0.00795333, -0.00160753
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionRiskTest7() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk("0000005");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findStyleAttributionRiskTest8() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk("0");
        Assert.assertEquals(0, fieldValueBeans.size());
    }

    @Test
    public void findStyleAttributionRiskTest9() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk(null);
        Assert.assertEquals(0, fieldValueBeans.size());
    }
}
