package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.Company;
import reaper.model.FundShortMessage;
import reaper.model.SpecialMessage;
import reaper.repository.CompanyRepository;
import reaper.repository.FundHoldStockRepository;
import reaper.repository.FundShortMessageRepository;
import reaper.repository.SprcialMessageRepository;

import java.io.*;

/**
 * Created by max on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class SpecialMessageTest {

    @Autowired
    SprcialMessageRepository sprcialMessageRepository;


    @Test
    public  void getFundShortMessageTest() {


        File file = new File("方差+夏普率.txt");


        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s;
            while ((s=bufferedReader.readLine())!=null){

                // System.out.println(s);
                String name=s.substring(0,6);
                String[] temp=s.split(" ");
                String fangcha1=temp[1];
                String fangcha2=temp[2];
                String fangcha3=temp[3];
                String xiapu1=temp[4];
                String xiapu2=temp[5];
                String xiapu3=temp[6];


                System.out.println(name+" "+fangcha1+" "+fangcha2);

                SpecialMessage specialMessage=new SpecialMessage();

                specialMessage.setCode(name);
                specialMessage.setStandardDeviation1(Double.parseDouble(fangcha1));
                specialMessage.setStandardDeviation2(Double.parseDouble(fangcha2));
                specialMessage.setStandardDeviation3(Double.parseDouble(fangcha3));

                specialMessage.setSharpeRatio1(Double.parseDouble(xiapu1));
                specialMessage.setSharpeRatio2(Double.parseDouble(xiapu2));
                specialMessage.setSharpeRatio3(Double.parseDouble(xiapu3));
                sprcialMessageRepository.save(specialMessage);




            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
