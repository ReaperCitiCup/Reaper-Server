package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.FundHoldBond;
import reaper.repository.FundHoldBondRepository;
import reaper.repository.FundHoldStockRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class CrawlTest {
    @Autowired
    FundHoldStockRepository fundHoldStockRepository;

    @Autowired
    FundHoldBondRepository fundHoldBondRepository;

    @Test
    public void fundHoldStockCrawl(){
        Code code = new Code();
        Crawler crawler = new Crawler();
        for (String c:code.getStockCode()){
            for(int i=2009;i<2018;i++){
                crawler.crawlFondHoldStock(c,String.valueOf(i),fundHoldStockRepository);
            }
        }
    }

    @Test
    public void fundHoldBondCrawl(){
        Code code = new Code();
        Crawler crawler = new Crawler();
        for (String c:code.getStockCode()){
            for(int i=2009;i<2018;i++){
                crawler.crawlFundHoldBond(c,String.valueOf(i),fundHoldBondRepository);
            }
        }

    }
}
