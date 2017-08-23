package reaper.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.FundHoldStock;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class FundHoldStockRepositoryTest {
    @Autowired
    FundHoldStockRepository fundHoldStockRepository;

    @Test
    public void testFindAllByStockCodeLike(){
        Pageable pageable = new PageRequest(0,10);
        Page<FundHoldStock> res = fundHoldStockRepository.findAllByStockCodeLike("%03%",pageable);
        System.out.println(res.getTotalElements());
        for(FundHoldStock r:res.getContent()){
            System.out.println(r);
        }
    }
}
