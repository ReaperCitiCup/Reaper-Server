package reaper.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.FundNetValue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class FundNetValueRepositoryTest {
    @Autowired
    FundNetValueRepository fundNetValueRepository;

    @Test
    public void testFindFirstByCodeOrderByDateDesc(){
        System.out.println(fundNetValueRepository.findFirstByCodeOrderByDateDesc("000003"));
    }
}
