package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.Company;
import reaper.model.FundCompany;
import reaper.model.FundManager;
import reaper.model.FundShortMessage;
import reaper.repository.*;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by max on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class FundManagerTest {

    @Autowired
    FundManagerRepository fundManagerRepository;


    @Test
    public  void getFundShortMessageTest() {



       getSingleCompanyFound();

    }
    public void getSingleCompanyFound(){


        File file = new File("FoundCodeandManagerCode.txt");

        FileReader fileReader= null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            String s;
            while ((s=bufferedReader.readLine())!=null){
                String[]  temp=s.split(" ");
                FundManager fundManager=new FundManager();
                fundManager.setFundCode(temp[0]);
                fundManager.setManagerId(temp[1]);
                fundManagerRepository.save(fundManager);
                System.out.println(fundManager.getFundCode()+" "+fundManager.getManagerId());
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
