package reaper.service;

import org.hibernate.mapping.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerBean;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.OptionalInt;

import static org.junit.Assert.*;

/**
 * Created by wuyuhan on 17/9/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ManagerServiceTest {

    private SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");

    @Autowired
    ManagerService managerService;

    //测试根据managerId查找manager

    //测试不存在的id=0
    @Test
    public void findManagerById1() throws Exception {
        ManagerBean managerBean=managerService.findManagerById("0");
        assertEquals(null,managerBean);
    }

    //测试存在的id=30304626
    @Test
    public void findManagerById2() throws Exception{
        ManagerBean managerBean=managerService.findManagerById("30304626");
        assertEquals("金梓才",managerBean.name);
        assertEquals(67.91,managerBean.bestReturns,0.000001);
        assertEquals("80161341",managerBean.company.id);
    }

    //测试根据managerId查找fundHistory

    //测试不存在的id=0
    @Test
    public void findFundHistoryById1() throws Exception {
        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("0");
        assertEquals(0,fundHistoryBeans.size());
    }

    //测试存在的id=30413691
    @Test
    public void findFundHistoryById2() throws Exception {
        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("30413691");
        int code=fundHistoryBeans.stream().mapToInt((x)->Integer.parseInt(x.id)).summaryStatistics().getMax();
        int time=fundHistoryBeans.stream().mapToInt((x)->x.days).summaryStatistics().getMin();
        assertEquals(2,fundHistoryBeans.size());
        assertEquals(60,time);
        assertEquals(4476,code);
    }

    //测试存在的id=30045022
    @Test
    public void findFundHistoryById3() throws Exception {
        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("30045022");
        fundHistoryBeans.sort(Comparator.comparing(h -> h.id));
        System.out.println(fundHistoryBeans.get(0).id);
        assertEquals(21,fundHistoryBeans.size());
        assertEquals(39.67,fundHistoryBeans.get(0).returns,0.000001);
        assertEquals("中低风险",fundHistoryBeans.get(0).type.get(1));
        assertEquals(796,fundHistoryBeans.get(16).days.intValue());
    }

    @Test
    public void findFundReturnsByManagerId() throws Exception {
    }

    @Test
    public void findFundRankByManagerId() throws Exception {
    }

    @Test
    public void findFundRateTrendByManagerId() throws Exception {
    }

    @Test
    public void findFundRankTrendByManagerId() throws Exception {
    }

    @Test
    public void findFundPerformanceByManagerId() throws Exception {
    }

    @Test
    public void findManagerPerformanceByManagerId() throws Exception {
    }

    @Test
    public void findManagerAbilityByManagerId() throws Exception {
    }

}