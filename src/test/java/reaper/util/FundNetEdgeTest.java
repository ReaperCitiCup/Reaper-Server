package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.FundNetEdge;
import reaper.model.ManagerRelation;
import reaper.repository.FundNetEdgeRepository;
import reaper.repository.ManagerRelationRepository;

import java.io.*;

/**
 * Created by max on 2017/9/11.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class FundNetEdgeTest {
    @Autowired
    FundNetEdgeRepository fundNetEdgeRepository;

    @Test
    public void solve(){
        getAllIn();
    }


    public void getAllIn(){
        File file=new File("fund_net_edge(2).csv");
        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s=null;
            while ((s=bufferedReader.readLine())!=null){

                //System.out.println(s);

                String[] temp = s.split(",");
                String newString = String.format("%06d", 5);

                    String code1 = String.format("%06d", Integer.parseInt(temp[1]));
                    String code2 = String.format("%06d", Integer.parseInt(temp[2]));
                    Double weight = Double.parseDouble(temp[3]);
                    FundNetEdge fundNetEdge=new FundNetEdge();
                    fundNetEdge.setCodeIdA(code1);
                    fundNetEdge.setCodeIdB(code2);
                    fundNetEdge.setWeight(weight);
                    fundNetEdgeRepository.save(fundNetEdge);

                    System.out.println(code1 + " " + code2 + " " + weight);



            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
