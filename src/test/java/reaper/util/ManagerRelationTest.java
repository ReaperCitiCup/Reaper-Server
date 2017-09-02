package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.ManagerRelation;
import reaper.repository.ManagerCompanyRespository;
import reaper.repository.ManagerRelationRepository;

import java.io.*;

/**
 * Created by max on 2017/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ManagerRelationTest {


    @Autowired
    ManagerRelationRepository managerRelationRepository;

    @Test
    public void main(){
        getAllIn();
    }


    public void getAllIn(){
        File file=new File("fund_net_edge(1).csv");
        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s=null;
            while ((s=bufferedReader.readLine())!=null){

                //System.out.println(s);

                    String[] temp = s.split(",");
                    String newString = String.format("%06d", 5);
                if(Integer.parseInt(temp[0])>32030) {
                    String code1 = String.format("%06d", Integer.parseInt(temp[1]));
                    String code2 = String.format("%06d", Integer.parseInt(temp[2]));
                    Double weight = Double.parseDouble(temp[3]);
                    ManagerRelation managerRelation = new ManagerRelation();
                    managerRelation.setCodeIdA(code1);
                    managerRelation.setCodeIdB(code2);
                    managerRelation.setWeight(weight);
                    managerRelationRepository.save(managerRelation);

                    System.out.println(code1 + " " + code2 + " " + weight);
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
