package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.FundRank;
import reaper.model.FundRankByType;
import reaper.model.FundShortMessage;
import reaper.repository.FundRankByTypeRepository;
import reaper.repository.FundShortMessageRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;

/**
 * Created by max on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class FundRankTest {

    @Autowired
    FundRankByTypeRepository fundRankByTypeRepository;



    @Test
    public  void getFundShortMessageTest() {


        File file = new File("/Users/max/Downloads/fund_rank_all.csv");


        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s;
            while ((s=bufferedReader.readLine())!=null){


                String[] temp=s.split(" ");

                String code=String.format("%06d", Integer.parseInt(temp[0]));

                String type="所有";

                Double score=Double.parseDouble(temp[1]);


                int rank=Integer.parseInt(temp[2]);

                int num=Integer.parseInt(temp[3]);
                FundRankByType fundRankByType=new FundRankByType();
                fundRankByType.setCode(code);
                fundRankByType.setRank(rank);
                fundRankByType.setScore(score);
                fundRankByType.setTotal(num);
                fundRankByType.setType(type);








            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
