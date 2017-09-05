package reaper.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerBean;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wuyuhan on 17/9/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ManagerServiceTest {

    @Autowired
    ManagerService managerService;

    //测试不存在的id
    @Test
    public void findManagerById1() throws Exception {
        ManagerBean managerBean=managerService.findManagerById("0");
        assertEquals(null,managerBean);
    }

    //测试存在的id
    @Test
    public void findManagerById2() throws Exception{
        ManagerBean managerBean=managerService.findManagerById("30304626");
        assertEquals("金梓才",managerBean.name);
        assertEquals(67.91,managerBean.bestReturns,0.000001);
    }

    //测试不存在的id
    @Test
    public void findFundHistoryById1() throws Exception {
        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("0");
        assertEquals(0,fundHistoryBeans.size());
    }

    @Test
    public void findFundHistoryById2() throws Exception {
        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("30036320");
//        assertEquals(34,fundHistoryBeans.size());
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