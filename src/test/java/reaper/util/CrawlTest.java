package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.FundHoldBond;
import reaper.repository.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class CrawlTest {
    @Autowired
    FundHoldStockRepository fundHoldStockRepository;

    @Autowired
    FundHoldBondRepository fundHoldBondRepository;

    @Autowired
    FundRepository fundRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    TotalPortionRepository totalPortionRepository;

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

    @Test
    public void fundDetailCrawl(){
        Code code = new Code();
        Crawler crawler = new Crawler();
        for (String c:code.getStockCode()){
            crawler.crawlFundDetail(c,fundRepository);
        }

    }

    @Test
    public void ManagerCrawl(){
        Code code = new Code();
        Crawler crawler = new Crawler();
        for(String id:code.getManagerCode()){
            crawler.crawlManager(id,managerRepository);
        }
    }

    @Test
    public void totalPortionCrawl(){
        Crawler crawler = new Crawler();
        for(int i=1;i<=123;i++)
            crawler.crawlTotalPortion(String.valueOf(i),totalPortionRepository);
    }
}
