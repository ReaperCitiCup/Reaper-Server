package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.Company;
import reaper.model.FundCompany;
import reaper.model.FundManager;
import reaper.model.CRP;
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
public class CRPTest {

    @Autowired
    CRPepository crPepository;


    @Test
    public  void getFundShortMessageTest() {



        getSingleCompanyFound();

    }
    public void getSingleCompanyFound(){


        File file = new File("/Users/max/Downloads/CPR(2).csv");

        FileReader fileReader= null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            String s;
            while ((s=bufferedReader.readLine())!=null){
                String[]  temp=s.split(",");
                String ID=temp[0];
                Integer LL=Integer.parseInt(temp[1]);
                Integer Wl=Integer.parseInt(temp[2]);
                Integer LW=Integer.parseInt(temp[3]);
                Integer WW=Integer.parseInt(temp[4]);
                Double CRP=Double.parseDouble(temp[5]);
                Integer CRPProportion=Integer.parseInt(temp[6]);
                Integer total=Integer.parseInt(temp[7]);


                CRP crp=new CRP();
                crp.setFundId(ID);
                crp.setLL(LL);
                crp.setWL(Wl);
                crp.setLW(LW);
                crp.setWW(WW);
                crp.setCPR(CRP);
                crp.setCRPProPortion(CRPProportion);
                crp.setTotal(total);

                crPepository.save(crp);





            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
