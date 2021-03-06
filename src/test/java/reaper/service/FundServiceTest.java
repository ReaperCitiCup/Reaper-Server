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
        reaper.util.Page<FundMiniBean> fundMiniBeanPage1=
                fundService.findFundByKeyword("成长", "code", 10, 2);
        reaper.util.Page<FundMiniBean> fundMiniBeanPage2=
                fundService.findFundByKeyword("成长", "name", 10, 2);
        reaper.util.Page<FundMiniBean> fundMiniBeanPage3=
                fundService.findFundByKeyword("成长", "code", 10, 10);
        reaper.util.Page<FundMiniBean> fundMiniBeanPage4=
                fundService.findFundByKeyword("成长", "code", 15, 2);
        reaper.util.Page<FundMiniBean> fundMiniBeanPage5=
                fundService.findFundByKeyword("成长", "code", 15, 10);
        int expectedSize=151;
        assertArrayEquals(
                new Integer[]{
                        expectedSize, expectedSize, expectedSize, expectedSize, expectedSize
                },
                new Integer[]{
                        fundMiniBeanPage1.getTotalCount(), fundMiniBeanPage2.getTotalCount(), fundMiniBeanPage3.getTotalCount(), fundMiniBeanPage4.getTotalCount(), fundMiniBeanPage5.getTotalCount()
                }
        );
        for (int i = 0; i < fundMiniBeanPage1.getResult().size() - 1; i++) {
            Assert.assertTrue(Integer.parseInt(fundMiniBeanPage1.getResult().get(i).code) < Integer.parseInt(fundMiniBeanPage1.getResult().get(i+1).code));
        }
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
                        fundBean.manager.size()+"", fundBean.manager.get(0).id, fundBean.manager.get(0).name, fundBean.company.id, fundBean.company.name
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

    //当fund为null时
    @Test
    public void findFundByCodeTest2() throws Exception{
        Assert.assertEquals(null, fundService.findFundByCode(null));
    }

    @Test
    public void findUnitNetValueTrendByCodeTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000005");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.02, valueDateBeans.get(1091).value.doubleValue(), 0.1);
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
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-21", valueDateBeans.get(1059).date);
        Assert.assertEquals(1.1382, valueDateBeans.get(1059).value.doubleValue(), 0.1);
    }

    @Test
    public void findUnitNetValueTrendByCodeTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000801");
        Assert.assertEquals(682, valueDateBeans.size());
        Assert.assertEquals("2014-11-04", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-22", valueDateBeans.get(681).date);
        Assert.assertEquals(1.123, valueDateBeans.get(681).value.doubleValue(), 0.1);
    }

    @Test
    public void findUnitNetValueTrendByCodeTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000948");
        Assert.assertEquals(598, valueDateBeans.size());
        Assert.assertEquals("2015-01-13", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-09-01", valueDateBeans.get(597).date);
        Assert.assertEquals(1.1944, valueDateBeans.get(597).value.doubleValue(), 0.1);
    }

    @Test
    public void findUnitNetValueTrendByCodeTest6() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("000950");
        Assert.assertEquals(637, valueDateBeans.size());
        Assert.assertEquals("2015-01-22", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-09-01", valueDateBeans.get(636).date);
        Assert.assertEquals(0.927, valueDateBeans.get(636).value.doubleValue(), 0.1);
    }

    @Test
    public void findUnitNetValueTrendByCodeTest7() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("5");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.02, valueDateBeans.get(1091).value.doubleValue(), 0.1);
    }

    @Test
    public void findUnitNetValueTrendByCodeTest8() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findUnitNetValueTrendByCode("005");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.02, valueDateBeans.get(1091).value.doubleValue(), 0.1);
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
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.203, valueDateBeans.get(1091).value.doubleValue(), 0.1);
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
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-21", valueDateBeans.get(1059).date);
        Assert.assertEquals(1.147, valueDateBeans.get(1059).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("000801");
        Assert.assertEquals(682, valueDateBeans.size());
        Assert.assertEquals("2014-11-04", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-22", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.123, valueDateBeans.get(1091).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("000948");
        Assert.assertEquals(598, valueDateBeans.size());
        Assert.assertEquals("2015-01-13", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-09-01", valueDateBeans.get(597).date);
        Assert.assertEquals(1.1944, valueDateBeans.get(597).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest6() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("5");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.203, valueDateBeans.get(1091).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest7() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("005");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(1.203, valueDateBeans.get(1091).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest8() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode("0");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest9() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeNetValueTrendByCode(null);
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCumulativeRateTrendByCodeTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "1");
        int length=7;
        Assert.assertEquals(length, valueDateBeans.size());
        Assert.assertEquals("2017-08-17", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(length-1).date);
        Assert.assertEquals(0, valueDateBeans.get(length-1).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeRateTrendByCodeTest2() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "3");
        int length=50;
        Assert.assertEquals(length, valueDateBeans.size());
        Assert.assertEquals("2017-06-19", valueDateBeans.get(0).date);
        Assert.assertEquals(0.1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(length-1).date);
        Assert.assertEquals(1.7, valueDateBeans.get(length-1).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeRateTrendByCodeTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "6");
        int length=111;
        Assert.assertEquals(length, valueDateBeans.size());
        Assert.assertEquals("2017-03-17", valueDateBeans.get(0).date);
        Assert.assertEquals(0.1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(length-1).date);
        Assert.assertEquals(2.18, valueDateBeans.get(length-1).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeRateTrendByCodeTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "12");
        int length=230;
        Assert.assertEquals(length, valueDateBeans.size());
        Assert.assertEquals("2016-09-19", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(length-1).date);
        Assert.assertEquals(1.18, valueDateBeans.get(length-1).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeRateTrendByCodeTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "36");
        int length=719;
        Assert.assertEquals(length, valueDateBeans.size());
        Assert.assertEquals("2014-09-17", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(length-1).date);
        Assert.assertEquals(17.22, valueDateBeans.get(length-1).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeRateTrendByCodeTest6() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000005", "all");
        Assert.assertEquals(1092, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(0, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(1091).date);
        Assert.assertEquals(19.92, valueDateBeans.get(1091).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeRateTrendByCodeTest7() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("5", "6");
        int length=111;
        Assert.assertEquals(length, valueDateBeans.size());
        Assert.assertEquals("2017-03-17", valueDateBeans.get(0).date);
        Assert.assertEquals(0.1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(length-1).date);
        Assert.assertEquals(2.18, valueDateBeans.get(length-1).value.doubleValue(), 0.1);
    }

    @Test
    public void findCumulativeRateTrendByCodeTest8() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("005", "6");
        int length=111;
        Assert.assertEquals(length, valueDateBeans.size());
        Assert.assertEquals("2017-03-17", valueDateBeans.get(0).date);
        Assert.assertEquals(0.1, valueDateBeans.get(0).value.doubleValue(), 0.1);
        Assert.assertEquals("2017-08-25", valueDateBeans.get(length-1).date);
        Assert.assertEquals(2.18, valueDateBeans.get(length-1).value.doubleValue(), 0.1);
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
        List<ValueDateBean> valueDateBeans=fundService.findCumulativeRateTrendByCode("000006", "6");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findCurrentAssetByCodeTest1() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode("000005");
        assertArrayEquals(
                new Double[]{
                        100.0, 0.0, 0.0, 0.0
                },
                new Double[]{
                        currentAssetBean.bond, currentAssetBean.stock, currentAssetBean.bank, currentAssetBean.other
                }
        );
    }

    @Test
    public void findCurrentAssetByCodeTest2() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode("000006");
        Assert.assertNull(currentAssetBean);
    }

    @Test
    public void findCurrentAssetByCodeTest3() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode("000007");
        assertArrayEquals(
                new Double[]{
                        100.0, 0.0, 0.0, 0.0
                },
                new Double[]{
                        currentAssetBean.bond, currentAssetBean.stock, currentAssetBean.bank, currentAssetBean.other
                }
        );
    }

    @Test
    public void findCurrentAssetByCodeTest4() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode("000948");
        assertArrayEquals(
                new Double[]{
                        0.0, 1.28, 0.0, 98.72
                },
                new Double[]{
                        currentAssetBean.bond, currentAssetBean.stock, currentAssetBean.bank, currentAssetBean.other
                }
        );
    }

    @Test
    public void findCurrentAssetByCodeTest5() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode("000950");
        assertArrayEquals(
                new Double[]{
                        0.0, 3.85, 0.0, 96.15
                },
                new Double[]{
                        currentAssetBean.bond, currentAssetBean.stock, currentAssetBean.bank, currentAssetBean.other
                }
        );
    }

    @Test
    public void findCurrentAssetByCodeTest6() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode("5");
        assertArrayEquals(
                new Double[]{
                        100.0, 0.0, 0.0, 0.0
                },
                new Double[]{
                        currentAssetBean.bond, currentAssetBean.stock, currentAssetBean.bank, currentAssetBean.other
                }
        );
    }

    @Test
    public void findCurrentAssetByCodeTest7() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode("005");
        assertArrayEquals(
                new Double[]{
                        100.0, 0.0, 0.0, 0.0
                },
                new Double[]{
                        currentAssetBean.bond, currentAssetBean.stock, currentAssetBean.bank, currentAssetBean.other
                }
        );
    }

    @Test
    public void findCurrentAssetByCodeTest8() throws Exception {
        CurrentAssetBean currentAssetBean=fundService.findCurrentAssetByCode(null);
        Assert.assertNull(currentAssetBean);
    }

    @Test
    public void findHistoryManagerByCodeTest1() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("000005");
        Assert.assertEquals(2, managerHistoryBeans.size());
        ManagerHistoryBean managerHistoryBean1=managerHistoryBeans.get(0);
        assertArrayEquals(
                new String[]{
                        "30198173", "刘宁", "2013-03-08", "至今", "1653", "21.63"
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
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("5");
        Assert.assertEquals(2, managerHistoryBeans.size());
        ManagerHistoryBean managerHistoryBean1=managerHistoryBeans.get(0);
        assertArrayEquals(
                new String[]{
                        "30198173", "刘宁", "2013-03-08", "至今", "1653", "21.63"
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
    public void findHistoryManagerByCodeTest4() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("005");
        Assert.assertEquals(2, managerHistoryBeans.size());
        ManagerHistoryBean managerHistoryBean1=managerHistoryBeans.get(0);
        assertArrayEquals(
                new String[]{
                        "30198173", "刘宁", "2013-03-08", "至今", "1653", "21.63"
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
    public void findHistoryManagerByCodeTest5() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode("0");
        Assert.assertEquals(0, managerHistoryBeans.size());
    }

    @Test
    public void findHistoryManagerByCodeTest6() throws Exception {
        List<ManagerHistoryBean> managerHistoryBeans=fundService.findHistoryManagerByCode(null);
        Assert.assertEquals(0, managerHistoryBeans.size());
    }

    @Test
    public void findJensenByCodeTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000948");
        Assert.assertEquals(931, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.000290714438132, valueDateBeans.get(0).value.doubleValue(), 0.01);
        Assert.assertEquals("2013-03-08", valueDateBeans.get(930).date);
        Assert.assertEquals(-0.00226152126828, valueDateBeans.get(930).value.doubleValue(), 0.01);
    }

    @Test
    public void findJensenByCodeTest2() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000006");
        Assert.assertNull(valueDateBeans);
    }
    @Test
    public void findJensenByCodeTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000007");
        Assert.assertEquals(903, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.00154304995858, valueDateBeans.get(0).value.doubleValue(), 0.01);
        Assert.assertEquals("2013-03-15", valueDateBeans.get(902).date);
        Assert.assertEquals(-0.00256104150246, valueDateBeans.get(902).value.doubleValue(), 0.01);
    }

    @Test
    public void findJensenByCodeTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000801");
        Assert.assertEquals(525, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.00125179956437, valueDateBeans.get(0).value.doubleValue(), 0.01);
        Assert.assertEquals("2014-11-04", valueDateBeans.get(524).date);
        Assert.assertEquals(-0.00234609226032, valueDateBeans.get(524).value.doubleValue(), 0.01);
    }

    @Test
    public void findJensenByCodeTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000948");
        Assert.assertEquals(442, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(0.00617736166631, valueDateBeans.get(0).value.doubleValue(), 0.01);
        Assert.assertEquals("2015-01-13", valueDateBeans.get(441).date);
        Assert.assertEquals(-0.00160075045976, valueDateBeans.get(441).value.doubleValue(), 0.01);
    }

    @Test
    public void findJensenByCodeTest6() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("000950");
        Assert.assertEquals(472, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(0.00450910674501, valueDateBeans.get(0).value.doubleValue(), 0.01);
        Assert.assertEquals("2013-03-08", valueDateBeans.get(471).date);
        Assert.assertEquals(-0.00509896744982, valueDateBeans.get(471).value.doubleValue(), 0.01);
    }

    @Test
    public void findJensenByCodeTest7() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("5");
        Assert.assertEquals(931, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.000290714438132, valueDateBeans.get(0).value.doubleValue(), 0.01);
        Assert.assertEquals("2013-03-08", valueDateBeans.get(930).date);
        Assert.assertEquals(-0.00226152126828, valueDateBeans.get(930).value.doubleValue(), 0.01);
    }

    @Test
    public void findJensenByCodeTest8() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("005");
        Assert.assertEquals(931, valueDateBeans.size());
        Assert.assertEquals("2016-12-30", valueDateBeans.get(0).date);
        Assert.assertEquals(-0.000290714438132, valueDateBeans.get(0).value.doubleValue(), 0.01);
        Assert.assertEquals("2013-03-08", valueDateBeans.get(930).date);
        Assert.assertEquals(-0.00226152126828, valueDateBeans.get(930).value.doubleValue(), 0.01);
    }

    @Test
    public void findJensenByCodeTest9() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findJensenByCode("0");
        Assert.assertEquals(0, valueDateBeans.size());
    }

    @Test
    public void findFundCompanyByCodeTest1() throws Exception {
        IdNameBean miniBean=fundService.findFundCompanyByCode("000005");
        Assert.assertEquals("80000223", miniBean.id);
        Assert.assertEquals("嘉实", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest2() throws Exception {
        IdNameBean miniBean=fundService.findFundCompanyByCode("000006");
        Assert.assertNull(miniBean);
    }

    @Test
    public void findFundCompanyByCodeTest3() throws Exception {
        IdNameBean miniBean=fundService.findFundCompanyByCode("000007");
        Assert.assertEquals("80000230", miniBean.id);
        Assert.assertEquals("鹏华", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest4() throws Exception {
        IdNameBean miniBean=fundService.findFundCompanyByCode("000948");
        Assert.assertEquals("80000222", miniBean.id);
        Assert.assertEquals("华夏", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest5() throws Exception {
        IdNameBean miniBean=fundService.findFundCompanyByCode("000950");
        Assert.assertEquals("80000229", miniBean.id);
        Assert.assertEquals("易方达", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest6() throws Exception {
        IdNameBean miniBean=fundService.findFundCompanyByCode("5");
        Assert.assertEquals("80000223", miniBean.id);
        Assert.assertEquals("嘉实", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest7() throws Exception {
        IdNameBean miniBean=fundService.findFundCompanyByCode("005");
        Assert.assertEquals("80000223", miniBean.id);
        Assert.assertEquals("嘉实", miniBean.name);
    }

    @Test
    public void findFundCompanyByCodeTest8() throws Exception {
        IdNameBean miniBean=fundService.findFundCompanyByCode("0");
        Assert.assertNull(miniBean);
    }

    @Test
    public void findFundCompanyByCodeTest9() throws Exception {
        IdNameBean miniBean=fundService.findFundCompanyByCode(null);
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
        Assert.assertNull(fieldValueBeans);
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
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findIndustryAttributionProfitTest9() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionProfit(null);
        Assert.assertNull(fieldValueBeans);
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
        Assert.assertNull(fieldValueBeans);
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
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findIndustryAttributionRiskTest9() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findIndustryAttributionRisk(null);
        Assert.assertNull(fieldValueBeans);
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
                        0.14, -0.01, 0.0, 0.04, -0.02,
                        -0.03, 0.02, -0.09, -0.02, -0.0
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
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findStyleAttributionProfitTest3() throws Exception {
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
                        0.14, -0.01, 0.0, 0.04, -0.02,
                        -0.03, 0.02, -0.09, -0.02, -0.0
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionProfitTest4() throws Exception {
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
                        0.14, -0.01, 0.0, 0.04, -0.02,
                        -0.03, 0.02, -0.09, -0.02, -0.0
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionProfitTest5() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionProfit("0");
        Assert.assertNull(fieldValueBeans);
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
                        0.56, -0.06, 0.19, 0.04, -0.33,
                        0.71, -1.22, -0.16, 0.8, 1.45
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
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findStyleAttributionRiskTest3() throws Exception {
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
                        0.56, -0.06, 0.19, 0.04, -0.33,
                        0.71, -1.22, -0.16, 0.8, 1.45
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionRiskTest4() throws Exception {
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
                        0.56, -0.06, 0.19, 0.04, -0.33,
                        0.71, -1.22, -0.16, 0.8, 1.45
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value, fieldValueBeans.get(9).value
                }
        );
    }

    @Test
    public void findStyleAttributionRiskTest5() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findStyleAttributionRisk("0");
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findVolatilityTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findVolatility("000005");
        Assert.assertEquals(1654, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1653);
        Assert.assertEquals("2017-09-16", valueDateBean2.date);
        Assert.assertEquals(0.0120829564008, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findVolatilityTest2() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findVolatility("5");
        Assert.assertEquals(1654, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1653);
        Assert.assertEquals("2017-09-16", valueDateBean2.date);
        Assert.assertEquals(0.0120829564008, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findVolatilityTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findVolatility("005");
        Assert.assertEquals(1654, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1653);
        Assert.assertEquals("2017-09-16", valueDateBean2.date);
        Assert.assertEquals(0.0120829564008, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findVolatilityTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findVolatility("000006");
        Assert.assertNull(valueDateBeans);
    }

    @Test
    public void findVolatilityTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findVolatility(null);
        Assert.assertNull(valueDateBeans);
    }

    @Test
    public void findValueAtRiskTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findValueAtRisk("000005");
        int length=1654;
        Assert.assertEquals(length, valueDateBeans.size());
        Assert.assertEquals("2013-03-08", valueDateBeans.get(0).date);
        Assert.assertEquals(0.00390415865188, valueDateBeans.get(length-1).value.doubleValue(), 0.1);
    }

    @Test
    public void findDownsideVolatilityTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findDownsideVolatility("000005");
        Assert.assertEquals(1653, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1652);
        Assert.assertEquals("2017-09-15", valueDateBean2.date);
        Assert.assertEquals(0.00390415865188, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findDownsideVolatilityTest2() throws Exception {
        List<ValueDateBean> valueDateBeans = fundService.findDownsideVolatility("000006");
        Assert.assertNull(valueDateBeans);
    }

    @Test
    public void findDownsideVolatilityTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findDownsideVolatility("005");
        Assert.assertEquals(1653, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1652);
        Assert.assertEquals("2017-09-15", valueDateBean2.date);
        Assert.assertEquals(0.00390415865188, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findDownsideVolatilityTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findDownsideVolatility("000006");
        Assert.assertNull(valueDateBeans);
    }

    @Test
    public void findDownsideVolatilityTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findDownsideVolatility(null);
        Assert.assertNull(valueDateBeans);
    }

    @Test
    public void findSharpeIndexTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findSharpeIndex("000005");
        Assert.assertEquals(1654, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1653);
        Assert.assertEquals("2017-09-16", valueDateBean2.date);
        Assert.assertEquals(-0.710244936746, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findSharpeIndexTest2() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findSharpeIndex("5");
        Assert.assertEquals(1654, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1653);
        Assert.assertEquals("2017-09-16", valueDateBean2.date);
        Assert.assertEquals(-0.710244936746, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findSharpeIndexTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findSharpeIndex("005");
        Assert.assertEquals(1654, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1653);
        Assert.assertEquals("2017-09-16", valueDateBean2.date);
        Assert.assertEquals(-0.710244936746, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findSharpeIndexTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findSharpeIndex("000006");
        Assert.assertNull(valueDateBeans);
    }

    @Test
    public void findSharpeIndexTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findSharpeIndex(null);
        Assert.assertNull(valueDateBeans);
    }

    @Test
    public void findTreynorIndexTest1() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findTreynorIndex("000005");
        Assert.assertEquals(1653, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1652);
        Assert.assertEquals("2017-09-15", valueDateBean2.date);
        Assert.assertEquals(-0.018293603251, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findTreynorIndexTest2() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findTreynorIndex("5");
        Assert.assertEquals(1653, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1652);
        Assert.assertEquals("2017-09-15", valueDateBean2.date);
        Assert.assertEquals(-0.018293603251, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findTreynorIndexTest3() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findTreynorIndex("005");
        Assert.assertEquals(1653, valueDateBeans.size());
        ValueDateBean valueDateBean1=valueDateBeans.get(0);
        Assert.assertEquals("2013-03-08", valueDateBean1.date);
        Assert.assertEquals(0.0, valueDateBean1.value.doubleValue(), 0.01);
        ValueDateBean valueDateBean2=valueDateBeans.get(1652);
        Assert.assertEquals("2017-09-15", valueDateBean2.date);
        Assert.assertEquals(-0.018293603251, valueDateBean2.value.doubleValue(), 0.01);
    }

    @Test
    public void findTreynorIndexTest4() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findTreynorIndex("000006");
        Assert.assertNull(valueDateBeans);
    }

    @Test
    public void findTreynorIndexTest5() throws Exception {
        List<ValueDateBean> valueDateBeans=fundService.findTreynorIndex(null);
        Assert.assertNull(valueDateBeans);
    }

    @Test
    public void findPerformanceIndexTest1() throws Exception {
        PerformanceIndexBean performanceIndexBean=fundService.findPerformanceIndex("000005");
        assertArrayEquals(
                new Double[]{
                        0.44, 36.36, 9.09
                },
                new Double[]{
                        performanceIndexBean.sustainabilityIndex, performanceIndexBean.loseDayRatio, performanceIndexBean.winDayRatio
                }
        );
    }

    @Test
    public void findPerformanceIndexTest2() throws Exception {
        PerformanceIndexBean performanceIndexBean=fundService.findPerformanceIndex("5");
        assertArrayEquals(
                new Double[]{
                        0.44, 36.36, 9.09
                },
                new Double[]{
                        performanceIndexBean.sustainabilityIndex, performanceIndexBean.loseDayRatio, performanceIndexBean.winDayRatio
                }
        );
    }

    @Test
    public void findPerformanceIndexTest3() throws Exception {
        PerformanceIndexBean performanceIndexBean=fundService.findPerformanceIndex("005");
        assertArrayEquals(
                new Double[]{
                        0.44, 36.36, 9.09
                },
                new Double[]{
                        performanceIndexBean.sustainabilityIndex, performanceIndexBean.loseDayRatio, performanceIndexBean.winDayRatio
                }
        );
    }

    @Test
    public void findPerformanceIndexTest4() throws Exception {
        PerformanceIndexBean performanceIndexBean=fundService.findPerformanceIndex("000006");
        Assert.assertNull(performanceIndexBean);
    }

    @Test
    public void findVarietyAttributionTest1() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findVarietyAttribution("000005");
        Assert.assertEquals(9, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "国债", "地方政府债","金融债", "企业债", "公司债", "中期票据", "短期融资券", "定向工具", "其他"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.0, 0.0, 40.78, 0.0, 0.0,
                        53.34, 0.0, 0.0, -144.8
                }, new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value
                }
        );
    }

    @Test
    public void findVarietyAttributionTest2() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findVarietyAttribution("5");
        Assert.assertEquals(9, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "国债", "地方政府债","金融债", "企业债", "公司债", "中期票据", "短期融资券", "定向工具", "其他"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.0, 0.0, 40.78, 0.0, 0.0,
                        53.34, 0.0, 0.0, -144.8
                }, new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value
                }
        );
    }

    @Test
    public void findVarietyAttributionTest3() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findVarietyAttribution("005");
        Assert.assertEquals(9, fieldValueBeans.size());
        assertArrayEquals(
                new String[]{
                        "国债", "地方政府债","金融债", "企业债", "公司债", "中期票据", "短期融资券", "定向工具", "其他"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field, fieldValueBeans.get(4).field,
                        fieldValueBeans.get(5).field, fieldValueBeans.get(6).field, fieldValueBeans.get(7).field, fieldValueBeans.get(8).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        0.0, 0.0, 40.78, 0.0, 0.0,
                        53.34, 0.0, 0.0, -144.8
                }, new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value, fieldValueBeans.get(4).value,
                        fieldValueBeans.get(5).value, fieldValueBeans.get(6).value, fieldValueBeans.get(7).value, fieldValueBeans.get(8).value
                }
        );
    }

    @Test
    public void findVarietyAttributionTest4() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findVarietyAttribution("000006");
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findVarietyAttributionTest5() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findVarietyAttribution(null);
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findBrisonAttributionStockTest1() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionStock("000005");
        assertArrayEquals(
                new String[]{
                        "资产配置效益", "股票选择效益", "交叉效益", "总超额效益"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        41.41, 40.39, -43.66, 38.14
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value
                }
        );
    }

    @Test
    public void findBrisonAttributionStockTest2() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionStock("5");
        assertArrayEquals(
                new String[]{
                        "资产配置效益", "股票选择效益", "交叉效益", "总超额效益"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        41.41, 40.39, -43.66, 38.14
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value
                }
        );
    }

    @Test
    public void findBrisonAttributionStockTest3() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionStock("005");
        assertArrayEquals(
                new String[]{
                        "资产配置效益", "股票选择效益", "交叉效益", "总超额效益"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        41.41, 40.39, -43.66, 38.14
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value
                }
        );
    }

    @Test
    public void findBrisonAttributionStockTest4() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionStock("000006");
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findBrisonAttributionStockTest5() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionStock(null);
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findBrisonAttributionBondTest1() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionBond("000005");
        assertArrayEquals(
                new String[]{
                        "资产配置效益", "债券选择效益", "交叉效益", "总超额效益"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        72.01, -3.48, -28.25, 40.28
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value
                }
        );
    }

    @Test
    public void findBrisonAttributionBondTest2() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionBond("5");
        assertArrayEquals(
                new String[]{
                        "资产配置效益", "债券选择效益", "交叉效益", "总超额效益"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        72.01, -3.48, -28.25, 40.28
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value
                }
        );
    }

    @Test
    public void findBrisonAttributionBondTest3() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionBond("005");
        assertArrayEquals(
                new String[]{
                        "资产配置效益", "债券选择效益", "交叉效益", "总超额效益"
                },
                new String[]{
                        fieldValueBeans.get(0).field, fieldValueBeans.get(1).field, fieldValueBeans.get(2).field, fieldValueBeans.get(3).field
                }
        );
        assertArrayEquals(
                new Double[]{
                        72.01, -3.48, -28.25, 40.28
                },
                new Double[]{
                        fieldValueBeans.get(0).value, fieldValueBeans.get(1).value, fieldValueBeans.get(2).value, fieldValueBeans.get(3).value
                }
        );
    }

    @Test
    public void findBrisonAttributionBondTest4() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionBond("000006");
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findBrisonAttributionBondTest5() throws Exception {
        List<FieldValueBean> fieldValueBeans=fundService.findBrisonAttributionBond(null);
        Assert.assertNull(fieldValueBeans);
    }

    @Test
    public void findChooseTimeTest1() throws Exception {
        List<ChooseBean> chooseBeans=fundService.findChooseStockTime("000005");
        Assert.assertEquals(13, chooseBeans.size());
        ChooseBean chooseBean=chooseBeans.get(0);
        Assert.assertEquals(
                new String[]{
                        "2016-09-15", "择时能力", "择股能力", "市场收益"
                },
                new String[]{
                        chooseBean.date, chooseBean.data.get(0).field, chooseBean.data.get(1).field, chooseBean.data.get(2).field
                }
        );
        Assert.assertEquals(
                new Double[]{
                        -0.13, -0.01, -3.31
                },
                new Double[]{
                        chooseBean.data.get(0).value, chooseBean.data.get(1).value, chooseBean.data.get(2).value
                }
        );
    }

    @Test
    public void findChooseTimeTest2() throws Exception {
        List<ChooseBean> chooseBeans=fundService.findChooseStockTime("5");
        Assert.assertEquals(13, chooseBeans.size());
        ChooseBean chooseBean=chooseBeans.get(0);
        Assert.assertEquals(
                new String[]{
                        "2016-09-15", "择时能力", "择股能力", "市场收益"
                },
                new String[]{
                        chooseBean.date, chooseBean.data.get(0).field, chooseBean.data.get(1).field, chooseBean.data.get(2).field
                }
        );
        Assert.assertEquals(
                new Double[]{
                        -0.13, -0.01, -3.31
                },
                new Double[]{
                        chooseBean.data.get(0).value, chooseBean.data.get(1).value, chooseBean.data.get(2).value
                }
        );
    }

    @Test
    public void findChooseTimeTest3() throws Exception {
        List<ChooseBean> chooseBeans=fundService.findChooseStockTime("005");
        Assert.assertEquals(13, chooseBeans.size());
        ChooseBean chooseBean=chooseBeans.get(0);
        Assert.assertEquals(
                new String[]{
                        "2016-09-15", "择时能力", "择股能力", "市场收益"
                },
                new String[]{
                        chooseBean.date, chooseBean.data.get(0).field, chooseBean.data.get(1).field, chooseBean.data.get(2).field
                }
        );
        Assert.assertEquals(
                new Double[]{
                        -0.13, -0.01, -3.31
                },
                new Double[]{
                        chooseBean.data.get(0).value, chooseBean.data.get(1).value, chooseBean.data.get(2).value
                }
        );
    }

    @Test
    public void findChooseTimeTest4() throws Exception {
        List<ChooseBean> chooseBeans=fundService.findChooseStockTime("000006");
        Assert.assertNull(chooseBeans);
    }

    @Test
    public void findChooseTimeTest5() throws Exception {
        List<ChooseBean> chooseBeans=fundService.findChooseStockTime(null);
        Assert.assertNull(chooseBeans);
    }

    @Test
    public void findFundPerformanceTest1() throws Exception {
        FundPerformanceBean fundPerformanceBean=fundService.findFundPerformance("000005");
         List<PerformanceDataBean> funds=fundPerformanceBean.funds;
        List<PerformanceDataBean> others=fundPerformanceBean.others;
        Assert.assertEquals(42, funds.size());
        Assert.assertEquals(5086, others.size());
    }

    @Test
    public void findFundPerformanceTest2() throws Exception {
        FundPerformanceBean fundPerformanceBean=fundService.findFundPerformance("000006");
        List<PerformanceDataBean> funds=fundPerformanceBean.funds;
        List<PerformanceDataBean> others=fundPerformanceBean.others;
        Assert.assertEquals(0, funds.size());
        Assert.assertEquals(5128, others.size());
    }

    @Test
    public void findManagerPerformanceTest1() throws Exception {
        ManagerPerformanceBean managerPerformanceBean=fundService.findManagerPerformance("000005");
        PerformanceDataBean manager=managerPerformanceBean.managers.get(0);
        assertArrayEquals(
                new String[]{
                        "30198173", "刘宁", "2954.0", "2.3"
                },
                new String[]{
                        manager.id, manager.name, manager.rate.toString(), manager.risk.toString()
                }
        );
        List<PerformanceDataBean> others=managerPerformanceBean.others;
        Assert.assertEquals(1391, others.size());
        PerformanceDataBean other=others.get(others.size()-1);
        assertArrayEquals(
                new String[]{
                        "30544899", "杜骐臻", "0.0", "8.25"
                },
                new String[]{
                        other.id, other.name, other.rate.toString(), other.risk.toString()
                }
        );
    }

    @Test
    public void findManagerPerformanceTest2() throws Exception {
        ManagerPerformanceBean managerPerformanceBean=fundService.findManagerPerformance("5");
        PerformanceDataBean manager=managerPerformanceBean.managers.get(0);
        assertArrayEquals(
                new String[]{
                        "30198173", "刘宁", "2954.0", "2.3"
                },
                new String[]{
                        manager.id, manager.name, manager.rate.toString(), manager.risk.toString()
                }
        );
        List<PerformanceDataBean> others=managerPerformanceBean.others;
        Assert.assertEquals(1391, others.size());
        PerformanceDataBean other=others.get(others.size()-1);
        assertArrayEquals(
                new String[]{
                        "30544899", "杜骐臻", "0.0", "8.25"
                },
                new String[]{
                        other.id, other.name, other.rate.toString(), other.risk.toString()
                }
        );
    }

    @Test
    public void findManagerPerformanceTest3() throws Exception {
        ManagerPerformanceBean managerPerformanceBean=fundService.findManagerPerformance("005");
        PerformanceDataBean manager=managerPerformanceBean.managers.get(0);
        assertArrayEquals(
                new String[]{
                        "30198173", "刘宁", "2954.0", "2.3"
                },
                new String[]{
                        manager.id, manager.name, manager.rate.toString(), manager.risk.toString()
                }
        );
        List<PerformanceDataBean> others=managerPerformanceBean.others;
        Assert.assertEquals(1391, others.size());
        PerformanceDataBean other=others.get(others.size()-1);
        assertArrayEquals(
                new String[]{
                        "30544899", "杜骐臻", "0.0", "8.25"
                },
                new String[]{
                        other.id, other.name, other.rate.toString(), other.risk.toString()
                }
        );
    }

    @Test
    public void findManagerPerformanceTest4() throws Exception {
        ManagerPerformanceBean managerPerformanceBean=fundService.findManagerPerformance("000006");
        Assert.assertEquals(0, managerPerformanceBean.managers.size());
        Assert.assertEquals(1392, managerPerformanceBean.others.size());
    }

    @Test
    public void findPublicOpinionTest1() throws Exception {
        List<PublicOpinionBean> publicOpinionBeanS=fundService.findPublicOpinion("000005");
        Assert.assertEquals(3, publicOpinionBeanS.size());
        PublicOpinionBean publicOpinionBean=publicOpinionBeanS.get(0);
        assertArrayEquals(
                new String[]{
                        "2017-05-21", "正面评价", "1", "负面评价", "0"
                },
                new String[]{
                        publicOpinionBean.date, publicOpinionBean.quantity.get(0).field, publicOpinionBean.quantity.get(0).value.toString(),
                        publicOpinionBean.quantity.get(1).field, publicOpinionBean.quantity.get(1).value.toString()
                }
        );
    }

    @Test
    public void findPublicOpinionTest2() throws Exception {
        List<PublicOpinionBean> publicOpinionBeanS=fundService.findPublicOpinion("5");
        Assert.assertEquals(3, publicOpinionBeanS.size());
        PublicOpinionBean publicOpinionBean=publicOpinionBeanS.get(0);
        assertArrayEquals(
                new String[]{
                        "2017-05-21", "正面评价", "1", "负面评价", "0"
                },
                new String[]{
                        publicOpinionBean.date, publicOpinionBean.quantity.get(0).field, publicOpinionBean.quantity.get(0).value.toString(),
                        publicOpinionBean.quantity.get(1).field, publicOpinionBean.quantity.get(1).value.toString()
                }
        );
    }

    @Test
    public void findPublicOpinionTest3() throws Exception {
        List<PublicOpinionBean> publicOpinionBeanS=fundService.findPublicOpinion("005");
        Assert.assertEquals(3, publicOpinionBeanS.size());
        PublicOpinionBean publicOpinionBean=publicOpinionBeanS.get(0);
        assertArrayEquals(
                new String[]{
                        "2017-05-21", "正面评价", "1", "负面评价", "0"
                },
                new String[]{
                        publicOpinionBean.date, publicOpinionBean.quantity.get(0).field, publicOpinionBean.quantity.get(0).value.toString(),
                        publicOpinionBean.quantity.get(1).field, publicOpinionBean.quantity.get(1).value.toString()
                }
        );
    }

    @Test
    public void findPublicOpinionTest4() throws Exception {
        List<PublicOpinionBean> publicOpinionBeanS=fundService.findPublicOpinion("000006");
        Assert.assertEquals(0, publicOpinionBeanS.size());
    }

    @Test
    public void findPublicOpinionTest5() throws Exception {
        List<PublicOpinionBean> publicOpinionBeanS=fundService.findPublicOpinion(null);
        Assert.assertEquals(0, publicOpinionBeanS.size());
    }

    @Test
    public void findPositionNetworkTest1() throws Exception {
        FundNetworkBean networkBean=fundService.findPositionNetwork("000005");
        Assert.assertEquals(0, networkBean.nodes.size());
        Assert.assertEquals(0, networkBean.links.size());
    }

    @Test
    public void findPositionNetworkTest2() throws Exception {
        FundNetworkBean networkBean=fundService.findPositionNetwork("5");
        Assert.assertEquals(0, networkBean.nodes.size());
        Assert.assertEquals(0, networkBean.links.size());
    }

    @Test
    public void findPositionNetworkTest3() throws Exception {
        FundNetworkBean networkBean=fundService.findPositionNetwork("005");
        Assert.assertEquals(0, networkBean.nodes.size());
        Assert.assertEquals(0, networkBean.links.size());
    }

    @Test
    public void findPositionNetworkTest4() throws Exception {
        FundNetworkBean networkBean=fundService.findPositionNetwork("000006");
        Assert.assertEquals(0, networkBean.nodes.size());
        Assert.assertEquals(0, networkBean.links.size());
    }

    @Test
    public void findPositionNetworkTest5() throws Exception {
        FundNetworkBean networkBean=fundService.findPositionNetwork("000007");
        Assert.assertEquals(3, networkBean.nodes.size());
        Assert.assertEquals(2, networkBean.links.size());
        assertArrayEquals(
                new String[]{
                        "汇丰晋信新动力混合", "鹏华国企债债券", "中海量化策略混合"
                },
                new String[]{
                        networkBean.nodes.get(0).name, networkBean.nodes.get(1).name, networkBean.nodes.get(2).name
                }
        );
        assertArrayEquals(
                new Double[]{
                        2.82, 3.28
                },
                new Double[]{
                        networkBean.links.get(0).value, networkBean.links.get(1).value
                }
        );
    }

}
