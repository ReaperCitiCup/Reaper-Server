package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.*;
import reaper.repository.*;

import javax.persistence.Id;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by max on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class StockBrinsonResultTest {

    @Autowired
    StockBrinsonResultRepository stockBrinsonResultRepository;



    @Test
    public  void getFundShortMessageTest() {



        getSingleCompanyFound();

    }
    public void getSingleCompanyFound(){


        File file = new File("/Users/max/Downloads/stock_brinson_result(1).csv");

        FileReader fileReader= null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            String s;
            while ((s=bufferedReader.readLine())!=null){
                String[]  temp=s.split(",");
                String ID=temp[0];
                if(Integer.parseInt(ID)>0) {
                    ID = String.format("%06d", Integer.parseInt(ID));
                }
                Double d1=null;

                Double d2=null;
                Double d3=null;
                Double d4=null;
                if (!temp[1].equals("NaN")) {
                    d1=Double.parseDouble(temp[1]);

                }
                if (!temp[2].equals("NaN")) {
                    d2 = Double.parseDouble(temp[2]);
                }
                    if (!temp[3].equals("NaN")) {
                        d3=Double.parseDouble(temp[3]);

                    }

                if (!temp[4].equals("NaN")) {

                     d4 = Double.parseDouble(temp[4]);
                }

                StockBrinsonResult stockBrinsonResult=new StockBrinsonResult();
                stockBrinsonResult.setAllocationEffect(d1);
                stockBrinsonResult.setSelectionEffect(d2);
                stockBrinsonResult.setInteractionEffect(d3);
                stockBrinsonResult.setActiveReturn(d4);
                stockBrinsonResult.setFundId(ID);


                stockBrinsonResultRepository.save(stockBrinsonResult);





            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
