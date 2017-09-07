package reaper.service;

import org.hibernate.mapping.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerBean;
import reaper.bean.ReturnBean;

import java.text.SimpleDateFormat;
import java.util.*;

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

    //测试不存在的id=0,mmmm
    @Test
    public void findManagerById1() throws Exception {
        assertArrayEquals(new ManagerBean[]{null,null},
                new ManagerBean[]{
                managerService.findManagerById("0"),managerService.findManagerById("mmmm")
        });
    }

    //测试存在的id=30304626
    @Test
    public void findManagerById2() throws Exception{
        ManagerBean managerBean=managerService.findManagerById("30304626");
        assertArrayEquals(new String[]{"金梓才",67.91+"","80161341"},
                new String[]{managerBean.name, managerBean.bestReturns+"",managerBean.company.id});
    }

    //测试根据managerId查找fundHistory

    //测试不存在的id=0,mmm
    @Test
    public void findFundHistoryById1() throws Exception {
        assertArrayEquals(new int[]{0,0},
                new int[]{
                        managerService.findFundHistoryById("0").size(),
                        managerService.findFundHistoryById("mmmm").size()
                });
    }

    //测试存在的id=30413691，时间是2017-09-06
    @Test
    public void findFundHistoryById2() throws Exception {
        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("30413691");
        int code=fundHistoryBeans.stream().mapToInt((x)->Integer.parseInt(x.id)).summaryStatistics().getMax();
        int time=fundHistoryBeans.stream().mapToInt((x)->x.days).summaryStatistics().getMin();
        assertArrayEquals(new int[]{2,61,4476},new int[]{fundHistoryBeans.size(),time,code});
    }

    //测试存在的id=30045022，时间是2017-09-06
    @Test
    public void findFundHistoryById3() throws Exception {
        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("30045022");
        fundHistoryBeans.sort(Comparator.comparing(h -> h.id));
        assertArrayEquals(new String[]{21+"",39.67+"","中低风险",796+""},
                new String[]{
                        fundHistoryBeans.size()+"",
                        fundHistoryBeans.get(0).returns+"",
                        fundHistoryBeans.get(0).type.get(1)+"",
                        fundHistoryBeans.get(16).days.intValue()+""
                });
    }

    //测试根据managerId查找其基金的收益

    //测试不存在的id=0,mmmm
    @Test
    public void findFundReturnsByManagerId1() throws Exception {
        assertArrayEquals(new int[]{0,0},
                new int[]{
                managerService.findFundReturnsByManagerId("0").size(),
                managerService.findFundReturnsByManagerId("mmmm").size()
                });
    }

    //测试存在的id=30132007
    @Test
    public void findFundReturnsByManagerId2() throws Exception {
        List<ReturnBean> returnBeans=managerService.findFundReturnsByManagerId("30132007");
        returnBeans.sort(Comparator.comparing((x)->x.id));
        assertArrayEquals(new String[]{17+"","000287","银华永利债券C",1.19+""},
                new String[]{
                        returnBeans.size()+"",
                        returnBeans.get(0).id,
                        returnBeans.get(1).name,
                        returnBeans.get(2).returns+""
                });
    }

    @Test
    public void findFundRankByManagerId() throws Exception {
    }

    //TODO
    //测试根据managerId查看rate trend

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

    //TODO
    //查看根据managerId查看manager ability

    @Test
    public void findManagerAbilityByManagerId() throws Exception {
    }

}