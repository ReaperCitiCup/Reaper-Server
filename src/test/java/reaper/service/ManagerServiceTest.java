package reaper.service;

import org.hibernate.mapping.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.bean.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by wuyuhan on 17/9/5.
 * 注意：
 * 本测试不仅包含黑盒测试，也包含白盒测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ManagerServiceTest {
//
//    private SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
//
//    @Autowired
//    ManagerService managerService;
//
//    //测试根据managerId查找manager
//
//    //测试不存在的id=0,mmmm
//    @Test
//    public void findManagerById1() throws Exception {
//        assertArrayEquals(new ManagerBean[]{null,null},
//                new ManagerBean[]{
//                managerService.findManagerById("0"),managerService.findManagerById("mmmm")
//        });
//    }
//
//    //测试存在的id=30304626
//    @Test
//    public void findManagerById2() throws Exception{
//        ManagerBean managerBean=managerService.findManagerById("30304626");
//        assertArrayEquals(new String[]{"金梓才",67.91+"","80161341"},
//                new String[]{managerBean.name, managerBean.bestReturns+"",managerBean.company.id});
//    }
//
//    //测试根据managerId查找fundHistory
//
//    //测试不存在的id=0,mmm
//    @Test
//    public void findFundHistoryById1() throws Exception {
//        assertArrayEquals(new int[]{0,0},
//                new int[]{
//                        managerService.findFundHistoryById("0").size(),
//                        managerService.findFundHistoryById("mmmm").size()
//                });
//    }
//
//    //测试存在的id=30413691，时间是2017-09-06
//    @Test
//    public void findFundHistoryById2() throws Exception {
//        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("30413691");
//        int code=fundHistoryBeans.stream().mapToInt((x)->Integer.parseInt(x.id)).summaryStatistics().getMax();
//        int time=fundHistoryBeans.stream().mapToInt((x)->x.days).summaryStatistics().getMin();
//        assertArrayEquals(new int[]{2,61,4476},new int[]{fundHistoryBeans.size(),time,code});
//    }
//
//    //测试存在的id=30045022，时间是2017-09-06
//    @Test
//    public void findFundHistoryById3() throws Exception {
//        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("30045022");
//        fundHistoryBeans.sort(Comparator.comparing(h -> h.id));
//        assertArrayEquals(new String[]{21+"",39.67+"","中低风险",796+""},
//                new String[]{
//                        fundHistoryBeans.size()+"",
//                        fundHistoryBeans.get(0).returns+"",
//                        fundHistoryBeans.get(0).type.get(1)+"",
//                        fundHistoryBeans.get(16).days +""
//                });
//    }
//
//    //测试根据managerId查找其基金的收益
//
//    //测试不存在的id=0,mmmm
//    @Test
//    public void findFundReturnsByManagerId1() throws Exception {
//        assertArrayEquals(new int[]{0,0},
//                new int[]{
//                managerService.findFundReturnsByManagerId("0").size(),
//                managerService.findFundReturnsByManagerId("mmmm").size()
//                });
//    }
//
//    //测试存在的id=30132007
//    @Test
//    public void findFundReturnsByManagerId2() throws Exception {
//        List<ReturnBean> returnBeans=managerService.findFundReturnsByManagerId("30132007");
//        returnBeans.sort(Comparator.comparing((x)->x.id));
//        assertArrayEquals(new String[]{17+"","000287","银华永利债券C",1.19+""},
//                new String[]{
//                        returnBeans.size()+"",
//                        returnBeans.get(0).id,
//                        returnBeans.get(1).name,
//                        returnBeans.get(2).returns+""
//                });
//    }
//
//    @Test
//    public void findFundRankByManagerId() throws Exception {
//    }
//
//    //测试根据managerId查看rate trend
//
//    //测试不存在的id=0，mmmm
//    @Test
//    public void findFundRateTrendByManagerId1() throws Exception {
//        assertArrayEquals(new int[]{0,0}, new int[]{
//                managerService.findFundRateTrendByManagerId("0").size(),
//                managerService.findFundRateTrendByManagerId("mmmm").size()
//        });
//    }
//
//    //测试存在的id=30505449,时间是2017-09-07
//    @Test
//    public void findFundRateTrendByManagerId2() throws Exception {
//        List<RateTrendBean> rateTrendBeans=managerService.findFundRateTrendByManagerId("30505449");
//        assertArrayEquals(new String[]{1+"",833+"","2014-03-19",1.0+""}, new String[]{
//                rateTrendBeans.size()+"",
//                rateTrendBeans.get(0).data.size()+"",
//                rateTrendBeans.get(0).data.get(0).date,
//                rateTrendBeans.get(0).data.get(0).value+""
//                });
//    }
//
//    //测试存在的id=30324725，时间是2017-09-07
//    @Test
//    public void findFundRateTrendByManagerId3() throws Exception {
//        List<RateTrendBean> rateTrendBeans=managerService.findFundRateTrendByManagerId("30324725");
//        rateTrendBeans.sort(Comparator.comparing((x)->x.id));
//        assertArrayEquals(new String[]{2+"",3115+"","160105","南方积极配置混合(LOF)",1.0602+""},new String[]{
//             rateTrendBeans.size()+"",
//             rateTrendBeans.get(1).data.size()+"",
//             rateTrendBeans.get(1).id,
//             rateTrendBeans.get(1).name,
//             rateTrendBeans.get(1).data.get(3114).value+""
//        });
//    }
//
//    //测试存在的id=30198307，时间是2017-09-07
//    @Test
//    public void findFundRateTrendByManagerId4() throws Exception {
//        List<RateTrendBean> rateTrendBeans=managerService.findFundRateTrendByManagerId("30198307");
//        rateTrendBeans.sort(Comparator.comparing((x)->x.id));
//        assertEquals(6,rateTrendBeans.size());
//    }
//
//    @Test
//    public void findFundRankTrendByManagerId() throws Exception {
//    }
//
//    //测试根据managerId查看该经理的基金的表现
//
//    //TODO 还差有效值
//    //测试不存在的id=0,mmmm
//    @Test
//    public void findFundPerformanceByManagerId1() throws Exception {
//        assertArrayEquals(new int[]{0,0}, new int[]{
//                managerService.findFundPerformanceByManagerId("0").size(),
//                managerService.findFundPerformanceByManagerId("mmmm").size()
//        });
//    }
//
//    //测试存在的id=30365855，时间是2017-09-10
//    @Test
//    public void findFundPerformanceByManagerId2() throws Exception {
//        List<FundPerformanceBean> fundPerformanceBeans=managerService.findFundPerformanceByManagerId("30365855");
//        assertArrayEquals(new String[]{1+"","001245","-7.53"}, new String[]{
//                fundPerformanceBeans.size()+"",
//                fundPerformanceBeans.get(0).id,
//                fundPerformanceBeans.get(0).rate+""
//                });
//    }
//
//    //测试存在的id=30070812，时间是2017-09-10
//    @Test
//    public void findFundPerformanceByManagerId3() throws Exception{
//        List<FundPerformanceBean> fundPerformanceBeans=managerService.findFundPerformanceByManagerId("30070812");
//        fundPerformanceBeans.sort(Comparator.comparing((x)->x.id));
//        assertArrayEquals(new String[]{7+"","004445"},new String[]{
//                fundPerformanceBeans.size()+"",
//                fundPerformanceBeans.get(3).id
//        });
//    }
//
//    //测试根据managerId查看经理的综合表现
//
//    //TODO 还差有效值
//    //测试不存在的id=0，mmmm
//    @Test
//    public void findManagerPerformanceByManagerId1() throws Exception {
//        assertArrayEquals(new int[]{0,0}, new int[]{
//                managerService.findManagerPerformanceByManagerId("0").size(),
//                managerService.findManagerPerformanceByManagerId("mmmm").size()
//        });
//    }
//
//    //TODO 还差有效值
//    //查看根据managerId查看manager ability
//
//    //测试不存在的id=0，mmmm
//    @Test
//    public void findManagerAbilityByManagerId1() throws Exception {
//        assertArrayEquals(new Object[]{null,null},
//                new Object[]{
//                        managerService.findManagerAbilityByManagerId("0"),
//                        managerService.findManagerAbilityByManagerId("mmmm")
//                });
//    }

}