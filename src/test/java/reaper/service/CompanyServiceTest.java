package reaper.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.bean.FundPerformanceBean;
import reaper.bean.PerformanceDataBean;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    //根据公司id获得公司旗下基金表现

    //不存在companyId=0，abc
    @Test
    public void findFundPerformanceByCompanyId1() throws Exception {
        assertArrayEquals(new int[]{0,0,5128,5128},new int[]{
                companyService.findFundPerformanceByCompanyId("0").funds.size(),
                companyService.findFundPerformanceByCompanyId("abc").funds.size(),
                companyService.findFundPerformanceByCompanyId("0").others.size(),
                companyService.findFundPerformanceByCompanyId("abc").others.size()
        });
    }

    //存在的companyId=80000080,funds部分，结合白盒测试
    //暂定应该排除fund中不存在的基金，爬虫并没有发挥什么作用
    @Test
    public void findFundPerformanceByCompanyId2() throws Exception{
        FundPerformanceBean fundPerformanceBean=companyService.findFundPerformanceByCompanyId("80000080");
        List<PerformanceDataBean> performanceDataBeans=fundPerformanceBean.funds;
        performanceDataBeans.sort(Comparator.comparing((x)->x.id));

        assertArrayEquals(new String[]{"4","002589","山西证券保本混合C","2.54","10.39"},new String[]{
            performanceDataBeans.size()+"",
            performanceDataBeans.get(0).id,
            performanceDataBeans.get(1).name,
            performanceDataBeans.get(2).rate+"",
            performanceDataBeans.get(3).risk+""
        });
    }

    //存在的companyId=80043374，others部分，结合白盒测试
    @Test
    public void findFundPerformanceByCompanyId3() throws Exception{
        List<PerformanceDataBean> performanceDataBeans=companyService.findFundPerformanceByCompanyId("80043374").others;
        performanceDataBeans.sort(Comparator.comparing((x)->x.id));
        assertArrayEquals(new String[]{"5070","000001","中海可转债A","-7.66","1.31"},new String[]{
            performanceDataBeans.size()+"",
            performanceDataBeans.get(0).id,
            performanceDataBeans.get(1).name,
            performanceDataBeans.get(2).rate+"",
            performanceDataBeans.get(3).risk+""
        });
    }

    @Test
    public void findManagerPerformanceByCompanyId() throws Exception {
    }

    @Test
    public void findProductStrategyByCompanyId() throws Exception {
    }

    @Test
    public void findAssetAllocationByCompanyId() throws Exception {
    }

    @Test
    public void findStyleAttributionProfitByCompanyId() throws Exception {
    }

    @Test
    public void findStyleAttributionRiskByCompanyId() throws Exception {
    }

    @Test
    public void findIndustryAttributionProfitByCompanyId() throws Exception {
    }

    @Test
    public void findIndustryAttributionRiskByCompanyId() throws Exception {
    }

}