package reaper.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.AssetAllocation;
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
    AssetAllocationRepository assetAllocationRepository;

    Code code;
    Crawler crawler;

    @Before
    public void prepare(){
        code = new Code();
        crawler = new Crawler();
    }

    @Test
    public void fundHoldStockCrawl(){

        for (String c:code.getStockCode()){
            for(int i=2009;i<2018;i++){
                crawler.crawlFondHoldStock(c,String.valueOf(i),fundHoldStockRepository);
            }
        }
    }

    @Test
    public void fundHoldBondCrawl(){
        for (String c:code.getStockCode()){
            for(int i=2009;i<2018;i++){
                crawler.crawlFundHoldBond(c,String.valueOf(i),fundHoldBondRepository);
            }
        }

    }

    @Test
    public void fundDetailCrawl(){
        for (String c:code.getStockCode()){
            crawler.crawlFundDetail(c,fundRepository);
        }

    }

    @Test
    public void ManagerCrawl(){
        for(String id:code.getManagerCode()){
            crawler.crawlManager(id,managerRepository);
        }
    }

    @Test
    public void AssetAllocationCrawl(){
        for(String c:code.getStockCode()){
            crawler.crawlAssetAllocation(c,assetAllocationRepository);
        }
    }
}
