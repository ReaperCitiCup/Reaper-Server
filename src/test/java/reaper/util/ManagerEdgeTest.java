package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.FundNetEdge;
import reaper.model.ManagerEdge;
import reaper.model.ManagerRelation;
import reaper.repository.FundNetEdgeRepository;
import reaper.repository.ManagerEdgeRepository;
import reaper.repository.ManagerRelationRepository;

import java.io.*;

/**
 * Created by max on 2017/9/11.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ManagerEdgeTest {
    @Autowired
    ManagerEdgeRepository managerEdgeRepository;

    @Test
    public void solve(){
        getAllIn();
    }


    public void getAllIn(){
        File file=new File("mng_edge.csv");
        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s=null;
            while ((s=bufferedReader.readLine())!=null){

                //System.out.println(s);

                String[] temp = s.split(",");

                String code1 = String.format("%08d", Integer.parseInt(temp[1]));
                String code2 = String.format("%08d", Integer.parseInt(temp[2]));
                int times=Integer.parseInt(temp[3]);
                int days=Integer.parseInt(temp[4]);

                ManagerEdge managerEdge=new ManagerEdge();
                managerEdge.setDays(days);
                managerEdge.setManagerIdA(code1);
                managerEdge.setManagerIdB(code2);
                managerEdge.setTimes(times);
                System.out.println(managerEdge.getManagerIdA() + " " + managerEdge.getManagerIdB()+ " " +managerEdge.getTimes()+" "+managerEdge.getDays());

                managerEdgeRepository.save(managerEdge);






            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
