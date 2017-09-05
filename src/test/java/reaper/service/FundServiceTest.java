package reaper.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * Created by Feng on 2017/9/5.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class FundServiceTest {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    FundService fundService;

    @Test
    public void findFundByKeywordTest() throws Exception {

    }

    @Test
    public void findFundByCodeTest() throws Exception {

    }

    @Test
    public void findUnitNetValueTrendByCodeTest() throws Exception {

    }

    @Test
    public void findCumulativeNetValueTrendByCodeTest() throws Exception {

    }

    @Test
    public void findCumulativeRateTrendByCodeTest() throws Exception {

    }

    @Test
    public void findHistoryManagersByCodeTest() throws Exception {

    }

    @Test
    public void findCurrentAssetByCodeTest() throws Exception {

    }

    @Test
    public void findHistoryManagerByCodeTest() throws Exception {

    }

}
