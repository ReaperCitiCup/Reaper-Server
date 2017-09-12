package reaper.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.bean.FieldValueBean;
import reaper.bean.FundPerformanceBean;
import reaper.bean.PerformanceDataBean;

import java.lang.reflect.Field;
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

    //根据公司id获得公司旗下经理表现

    //不存在companyId=0，abc
    @Test
    public void findManagerPerformanceByCompanyId1() throws Exception {
        assertArrayEquals(new int[]{0,0,1392,1392},new int[]{
                companyService.findManagerPerformanceByCompanyId("0").managers.size(),
                companyService.findManagerPerformanceByCompanyId("abc").managers.size(),
                companyService.findManagerPerformanceByCompanyId("0").others.size(),
                companyService.findManagerPerformanceByCompanyId("abc").others.size()
        });
    }

    //TODO rate有问题
    //存在的companyId=80036797，managers部分，结合白盒测试
    @Test
    public void findManagerPerformanceByCompanyId2() throws Exception{
        List<PerformanceDataBean> performanceDataBeans=companyService.findManagerPerformanceByCompanyId("80036797").managers;
        performanceDataBeans.sort(Comparator.comparing((x)->x.id));
        assertArrayEquals(new String[]{"9","30047657","施同亮","6.56","0.01"},new String[]{
            performanceDataBeans.size()+"",
            performanceDataBeans.get(0).id,
            performanceDataBeans.get(1).name,
            performanceDataBeans.get(2).rate+"",
            performanceDataBeans.get(3).risk+""
        });
    }

    //TODO rate有问题
    //存在的companyId=80000245，others部分，结合白盒测试
    @Test
    public void findManagerPerformanceByCompanyId3() throws Exception{
        List<PerformanceDataBean> performanceDataBeans=companyService.findManagerPerformanceByCompanyId("80000245").others;
        performanceDataBeans.sort(Comparator.comparing((x)->x.id));
        assertArrayEquals(new String[]{"1381","30036308","韩会永","","11.78"},new String[]{
                performanceDataBeans.size()+"",
                performanceDataBeans.get(0).id,
                performanceDataBeans.get(1).name,
                performanceDataBeans.get(2).rate+"",
                performanceDataBeans.get(3).risk+""
        });
    }

    //根据公司id获得公司资产配置行业占比

    //TODO 抛出异常
    //不存在companyId=0，abc
    @Test
    public void findAssetAllocationByCompanyId1() throws Exception {
        companyService.findAssetAllocationByCompanyId("0");
    }

    //根据公司id获得公司风格归因-主动收益

    //不存在companyId=0，abc
    @Test
    public void findStyleAttributionProfitByCompanyId1() throws Exception {
        assertEquals(null,companyService.findStyleAttributionProfitByCompanyId("0"));
        assertEquals(null,companyService.findStyleAttributionProfitByCompanyId("abc"));
    }

    //存在的companyId=80041198，结合白盒测试
    @Test
    public void findStyleAttributionProfitByCompanyId2() throws Exception{
        List<FieldValueBean> fieldValueBeans=companyService.findStyleAttributionProfitByCompanyId("80041198");
        assertArrayEquals(new String[]{"10","beta"},new String[]{
                fieldValueBeans.size()+"",
                fieldValueBeans.get(0).field,
        });
        assertEquals(-0.0006704067896377638,fieldValueBeans.get(1).value,0.000001);
    }

    //factor_result中不存在的companyId=80560392,结合白盒测试
    @Test
    public void findStyleAttributionProfitByCompanyId3() throws Exception{
        assertEquals(null,companyService.findStyleAttributionProfitByCompanyId("80560392"));
    }

    //根据公司id获得公司风格归因-主动风险

    //不存在companyId=0，abc
    @Test
    public void findStyleAttributionRiskByCompanyId1() throws Exception {
        assertEquals(null,companyService.findStyleAttributionRiskByCompanyId("0"));
        assertEquals(null,companyService.findStyleAttributionRiskByCompanyId("abc"));
    }

    //存在的companyId=80000229，结合白盒测试
    @Test
    public void findStyleAttributionRiskByCompanyId2() throws Exception{
        List<FieldValueBean> fieldValueBeans=companyService.findStyleAttributionRiskByCompanyId("80000229");
        assertArrayEquals(new String[]{"10","价值"},new String[]{
            fieldValueBeans.size()+"",
            fieldValueBeans.get(1).field
        });
        assertEquals(-0.00005608566791481931,fieldValueBeans.get(2).value,0.000001);
    }

    //factor_result中不存在的companyId=80538609,结合白盒测试
    @Test
    public void findStyleAttributionRiskByCompanyId3() throws Exception{
        assertEquals(null,companyService.findStyleAttributionRiskByCompanyId("80538609"));
    }

    //根据公司id获得公司行业归因-主动收益

    //不存在companyId=0，abc
    @Test
    public void findIndustryAttributionProfitByCompanyId1() throws Exception {
        assertEquals(null,companyService.findIndustryAttributionProfitByCompanyId("0"));
        assertEquals(null,companyService.findIndustryAttributionProfitByCompanyId("abc"));
    }

    //存在的companyId=80036782，结合白盒测试
    @Test
    public void findIndustryAttributionProfitByCompanyId2() throws Exception{
        List<FieldValueBean> fieldValueBeans=companyService.findIndustryAttributionProfitByCompanyId("80036782");
        assertArrayEquals(new String[]{"30","综合"},new String[]{
                fieldValueBeans.size()+"",
                fieldValueBeans.get(0).field
        });
        assertEquals(0.0023966964630367836,fieldValueBeans.get(1).value,0.000001);
    }

    //factor_result中不存在的companyId=80488954,结合白盒测试
    @Test
    public void findIndustryAttributionProfitByCompanyId3() throws Exception{
        assertEquals(null,companyService.findIndustryAttributionProfitByCompanyId("80488954"));
    }

    //根据公司id获得公司行业归因-主动风险

    //不存在companyId=0，abc
    @Test
    public void findIndustryAttributionRiskByCompanyId1() throws Exception {
        assertEquals(null,companyService.findIndustryAttributionRiskByCompanyId("0"));
        assertEquals(null,companyService.findIndustryAttributionRiskByCompanyId("abc"));
    }

    //存在的companyId=80365986
    @Test
    public void findIndustryAttributionRiskByCompanyId2() throws Exception{
        List<FieldValueBean> fieldValueBeans=companyService.findIndustryAttributionRiskByCompanyId("80365986");
        assertArrayEquals(new String[]{"30","机械"},new String[]{
            fieldValueBeans.size()+"",
            fieldValueBeans.get(1).field
        });
        assertEquals(-0.1577792721384206,fieldValueBeans.get(2).value,0.0000001);
    }

    //factor_result中不存在的companyId=80056613,结合白盒测试
    @Test
    public void findIndustryAttributionRiskByCompanyId3() throws Exception{
        assertEquals(null,companyService.findIndustryAttributionRiskByCompanyId("80056613"));
    }

}