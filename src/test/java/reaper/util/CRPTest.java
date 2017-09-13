package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.CPR;
import reaper.repository.CPRRepository;

import java.io.*;


/**
 * Created by max on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class CRPTest {

    @Autowired
    CPRRepository cprRepository;


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


                CPR crp=new CPR();
                crp.setFundId(ID);
                crp.setLL(LL);
                crp.setWL(Wl);
                crp.setLW(LW);
                crp.setWW(WW);
                crp.setCPR(CRP);
                crp.setCRPProPortion(CRPProportion);
                crp.setTotal(total);

                cprRepository.save(crp);





            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
