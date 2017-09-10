package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.ManagerRelation;
import reaper.model.ManagerRemark;
import reaper.repository.ManagerRelationRepository;
import reaper.repository.ManagerRemarkRepository;

import java.io.*;

/**
 * Created by max on 2017/9/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ManagerRemarkTest {


    @Autowired
    ManagerRemarkRepository managerRemarkRepository;

    @Test
    public void main(){
        getAllIn();
    }




    public void  getAllIn(){

        File file=new File("基金经理评价(3).csv");
        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s;
            while ((s=bufferedReader.readLine())!=null){

                String [] strings=s.split(",");
                ManagerRemark managerRemark=new ManagerRemark();
                managerRemark.setManagerId(Integer.parseInt(strings[0]));
                managerRemark.setAverageYieldRate(Double.parseDouble(strings[1]));
                managerRemark.setYieldAbility(Integer.parseInt(strings[2]));
                managerRemark.setAverageFluctuationRatio(Double.parseDouble(strings[3]));
                managerRemark.setWindControlAbility(Integer.parseInt(strings[4]));
                managerRemark.setTimingCoefficient(Double.parseDouble(strings[5]));
                managerRemark.setMarketTimingAbility(Integer.parseInt(strings[6]));
                managerRemark.setStockSelectionCoefficient(Double.parseDouble(strings[7]));
                managerRemark.setStockOptionAbility(Integer.parseInt(strings[8]));
                managerRemark.setWeightedTenureLength(Double.parseDouble(strings[9]));
                managerRemark.setExperience(Integer.parseInt(strings[10]));
                managerRemark.setAverageAbility(Double.parseDouble(strings[11]));


                managerRemarkRepository.save(managerRemark);


                System.out.println(s);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
