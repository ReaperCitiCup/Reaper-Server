package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.BrisonResult;
import reaper.model.FactorResult;
import reaper.repository.BrisonResultRepository;
import reaper.repository.FactorResultRepository;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class CsvSaver {
    @Autowired
    FactorResultRepository factorResultRepository;

    @Autowired
    BrisonResultRepository brisonResultRepository;

    SaveFactorResult saveFactorResult = new SaveFactorResult();

    @Test
    public void saveFactorResult(){
        for(FactorResult factorResult:saveFactorResult.saveFactorResult("risk_")){
            System.out.println(factorResult);
            factorResultRepository.save(factorResult);
        }
    }

    @Test
    public void saveBrisonResult(){
        for(BrisonResult brisonResult:saveFactorResult.saveBrinsonResult()){
            System.out.println(brisonResult);
            brisonResultRepository.save(brisonResult);
        }
    }

     @Test
    public void updateFundInfo(){
         File file = new File("");
    }
}
