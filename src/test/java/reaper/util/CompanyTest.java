package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.Company;
import reaper.model.FundShortMessage;
import reaper.repository.CompanyRepository;
import reaper.repository.FundHoldStockRepository;
import reaper.repository.FundShortMessageRepository;

import java.io.*;

/**
 * Created by max on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class CompanyTest {

    @Autowired
    CompanyRepository companyRepository;


    @Test
    public  void getFundShortMessageTest() {


        File file = new File("CompanyNameandCode副本.txt");


        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s;
            while ((s=bufferedReader.readLine())!=null){

                // System.out.println(s);
                String[] temp=s.split(" ");
                String code=temp[0];
                String name=temp[1];
                System.out.println(code+name);

                Company company=new Company();
                company.setCompanyId(code);
                company.setName(name);
                companyRepository.save(company);




            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
