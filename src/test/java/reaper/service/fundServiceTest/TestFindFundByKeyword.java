package reaper.service.fundServiceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.service.FundService;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Feng on 2017/9/7.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(Parameterized.class)
@SpringBootTest()
public class TestFindFundByKeyword {
    @Autowired
    FundService fundService;

    @Parameterized.Parameters
    public static Collection words(){
        return Arrays.asList(new Object[][]{
                {},
                {},
                {}
        });
    }

    public TestFindFundByKeyword(){

    }

    @Test
    public void findFundByKeywordTest(){

    }
}
