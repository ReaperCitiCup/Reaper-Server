package reaper.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CompanyControllerTest {

    @InjectMocks
    CompanyController companyController;

    private MockMvc mockMvc;

    @Before
    public void beforeInit(){
        MockitoAnnotations.initMocks(this);
        companyController=new CompanyController();
        mockMvc= MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    public void findFundPerformanceByCompanyId() throws Exception {
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/company/80043374/fund-performance").
                    accept(MediaType.APPLICATION_JSON)).andReturn();
            System.out.println(result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findManagerPerformanceByCompanyId() throws Exception {
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