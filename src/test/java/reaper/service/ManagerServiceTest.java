package reaper.service;

import org.hibernate.mapping.Collection;
import org.junit.Assert;
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

    //测试存在的id=30413691，时间是2017-09-16
    @Test
    public void findFundHistoryById2() throws Exception {
        List<FundHistoryBean> fundHistoryBeans=managerService.findFundHistoryById("30413691");
        int code=fundHistoryBeans.stream().mapToInt((x)->Integer.parseInt(x.id)).summaryStatistics().getMax();
        int time=fundHistoryBeans.stream().mapToInt((x)->x.days).summaryStatistics().getMin();
        assertArrayEquals(new int[]{2,4476},new int[]{fundHistoryBeans.size(),code});
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
                        fundHistoryBeans.get(16).days +""
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
    public void findFundRankByManagerId1() throws Exception {
        List<RankBean> rankBeans=managerService.findFundRankByManagerId("30132007");
        Assert.assertEquals(7, rankBeans.size());
        RankBean rankBean=rankBeans.get(0);
        assertArrayEquals(
                new String[]{
                        "180015", "银华增强债券", "所有", "债券型", "中风险"
                },new String[]{
                        rankBean.id, rankBean.name, rankBean.data.get(0).type, rankBean.data.get(1).type, rankBean.data.get(2).type
                }
        );
        assertArrayEquals(
                new Integer[]{
                        3853, 5298, 498, 1274, 282, 511
                },
                new Integer[]{
                        rankBean.data.get(0).rank, rankBean.data.get(0).total, rankBean.data.get(1).rank, rankBean.data.get(1).total,rankBean.data.get(2).rank, rankBean.data.get(2).total
                }
        );
    }

    @Test
    public void findFundRankByManagerId() throws Exception {
        List<RankBean> rankBeans=managerService.findFundRankByManagerId("0");
        Assert.assertEquals(0, rankBeans.size());
    }

    @Test
    public void findFundRateTrendByManagerId1() throws Exception {
        assertArrayEquals(new int[]{0,0}, new int[]{
                managerService.findFundRateTrendByManagerId("0").size(),
                managerService.findFundRateTrendByManagerId("mmmm").size()
        });
    }

    //测试存在的id=30505449,时间是2017-09-07
    @Test
    public void findFundRateTrendByManagerId2() throws Exception {
        List<RateTrendBean> rateTrendBeans=managerService.findFundRateTrendByManagerId("30505449");
        assertArrayEquals(new String[]{1+"",833+"","2014-03-19",1.0+""}, new String[]{
                rateTrendBeans.size()+"",
                rateTrendBeans.get(0).data.size()+"",
                rateTrendBeans.get(0).data.get(0).date,
                rateTrendBeans.get(0).data.get(0).value+""
                });
    }

    //测试存在的id=30324725，时间是2017-09-07
    @Test
    public void findFundRateTrendByManagerId3() throws Exception {
        List<RateTrendBean> rateTrendBeans=managerService.findFundRateTrendByManagerId("30324725");
        rateTrendBeans.sort(Comparator.comparing((x)->x.id));
        assertArrayEquals(new String[]{2+"","160105","南方积极配置混合(LOF)",1.0602+""},new String[]{
             rateTrendBeans.size()+"",
             rateTrendBeans.get(1).id,
             rateTrendBeans.get(1).name,
             rateTrendBeans.get(1).data.get(3114).value+""
        });
    }

    //测试存在的id=30198307，时间是2017-09-07
    @Test
    public void findFundRateTrendByManagerId4() throws Exception {
        List<RateTrendBean> rateTrendBeans=managerService.findFundRateTrendByManagerId("30198307");
        rateTrendBeans.sort(Comparator.comparing((x)->x.id));
        assertEquals(6,rateTrendBeans.size());
    }

    //测试不存在的id=0,mmmm
    @Test
    public void findFundPerformanceByManagerId1() throws Exception {
        FundPerformanceBean fundPerformanceBean1=managerService.findFundPerformanceByManagerId("0");
        FundPerformanceBean fundPerformanceBean2=managerService.findFundPerformanceByManagerId("mmmm");
        assertArrayEquals(
                new Integer[]{
                        0, 5298, 0, 5298
                },
                new Integer[]{
                        fundPerformanceBean1.funds.size(), fundPerformanceBean1.others.size(), fundPerformanceBean2.funds.size(), fundPerformanceBean2.others.size()
                }
        );
    }

    @Test
    public void findFundPerformanceByManagerId2() throws Exception {
        FundPerformanceBean fundPerformanceBean=managerService.findFundPerformanceByManagerId("30198173");
        List<PerformanceDataBean> funds=fundPerformanceBean.funds;
        Assert.assertEquals(15, funds.size());
        List<PerformanceDataBean> others=fundPerformanceBean.others;
        Assert.assertEquals(5090, others.size());
        assertArrayEquals(
                new String[]{
                        "000005"
                },
                new String[]{
                        funds.get(0).id
                }
        );
        PerformanceDataBean other=others.get(2);
        assertArrayEquals(
                new String[]{
                        "000004", "中海可转债C", "-7.66", "7.35"
                },
                new String[]{
                        other.id, other.name, other.rate.toString(), other.risk.toString()
                }
        );
    }

    //测试不存在的id=0，mmmm
    @Test
    public void findManagerPerformanceByManagerId1() throws Exception {
        ManagerPerformanceBean managerPerformanceBean1=managerService.findManagerPerformanceByManagerId("0");
        ManagerPerformanceBean managerPerformanceBean2=managerService.findManagerPerformanceByManagerId("mmmm");
        assertArrayEquals(
                new Integer[]{
                        0, 1392, 0, 1392
                },
                new Integer[]{
                        managerPerformanceBean1.managers.size(), managerPerformanceBean1.others.size(), managerPerformanceBean2.managers.size(), managerPerformanceBean2.others.size()
                }
        );
    }

    @Test
    public void findManagerPerformanceByManagerId2() throws Exception {
        ManagerPerformanceBean managerPerformanceBean=managerService.findManagerPerformanceByManagerId("30198173");
        Assert.assertEquals(1, managerPerformanceBean.managers.size());
        Assert.assertEquals(1391, managerPerformanceBean.others.size());
        PerformanceDataBean performanceDataBean=managerPerformanceBean.managers.get(0);
        assertArrayEquals(
                new String[]{
                        "30198173", "刘宁", "29.54", "2.3"
                },
                new String[]{
                        performanceDataBean.id, performanceDataBean.name, performanceDataBean.rate.toString(), performanceDataBean.risk.toString()
                }
        );
    }

    //测试不存在的id=0，mmmm
    @Test
    public void findManagerAbilityByManagerId1() throws Exception {
        assertArrayEquals(new Object[]{null,null},
                new Object[]{
                        managerService.findManagerAbilityByManagerId("0"),
                        managerService.findManagerAbilityByManagerId("mmmm")
                });
    }

    @Test
    public void findSocialNetworkByManagerId1() throws Exception{
        ManagerNetworkBean managerNetworkBean=managerService.findSocialNetworkByManagerId("30198442");
        Assert.assertEquals(0, managerNetworkBean.nodes.size());
        Assert.assertEquals(0, managerNetworkBean.links.size());
    }

    @Test
    public void findSocialNetworkByManagerId2() throws Exception{
        ManagerNetworkBean managerNetworkBean1=managerService.findSocialNetworkByManagerId("0");
        Assert.assertEquals(0, managerNetworkBean1.nodes.size());
        Assert.assertEquals(0, managerNetworkBean1.links.size());
        ManagerNetworkBean managerNetworkBean2=managerService.findSocialNetworkByManagerId("mmmm");
        Assert.assertEquals(0, managerNetworkBean2.nodes.size());
        Assert.assertEquals(0, managerNetworkBean2.links.size());
    }

    @Test
    public void findSocialNetworkByManagerId3() throws Exception{
        ManagerNetworkBean managerNetworkBean=managerService.findSocialNetworkByManagerId("30284601");
        Assert.assertEquals(3, managerNetworkBean.nodes.size());
        Assert.assertEquals(2, managerNetworkBean.links.size());
        for(NodeDataBean nodeDataBean:managerNetworkBean.nodes){
            System.out.println(nodeDataBean.code);
        }
        assertArrayEquals(
                new String []{
                        "陈正宪", "何如", "刘珈吟"
                },
                new String[]{
                        managerNetworkBean.nodes.get(0).code, managerNetworkBean.nodes.get(1).code, managerNetworkBean.nodes.get(2).code
                }
        );
        ManagerLinkDataBean managerLinkDataBean=managerNetworkBean.links.get(0);
        assertArrayEquals(
                new Integer[]{
                        0, 1, 12521, 20
                },
                new Integer[]{
                        managerLinkDataBean.source, managerLinkDataBean.target, managerLinkDataBean.days, managerLinkDataBean.times
                }
        );
    }

}