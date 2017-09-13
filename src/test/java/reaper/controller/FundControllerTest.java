package reaper.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by Feng on 2017/9/12.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest()
public class FundControllerTest {
    private MockMvc mvc;

    @Before
    public void beforeInit() {
        mvc = MockMvcBuilders.standaloneSetup(new FundController()).build();
    }

    @Test
    public void test() {
        try {
            MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/fund/000005").accept(MediaType.APPLICATION_JSON)).andReturn();
            System.out.println(result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
