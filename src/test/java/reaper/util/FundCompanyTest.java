package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.Company;
import reaper.model.FundCompany;
import reaper.model.FundShortMessage;
import reaper.repository.CompanyRepository;
import reaper.repository.FundCompanyRepository;
import reaper.repository.FundHoldStockRepository;
import reaper.repository.FundShortMessageRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by max on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class FundCompanyTest {

    @Autowired
    FundCompanyRepository fundCompanyRepository;


    @Test
    public  void getFundShortMessageTest() {


        Code code=new Code();
        ArrayList<String> codes=code.getStockCode();


        getSingleCompanyFound("80560392");
//        int i;
//        for(i=0;i<codes.size();i++){
//            getSingleCompanyFound("80560392");
//        }



    }
    public void getSingleCompanyFound(String code){


        File file = new File("CompanyHoldOnFoundCode.txt");

        MyBug myBug = new MyBug();
        String allmessage = myBug.sendPost("http://fund.eastmoney.com/company/"+code+".html" , "");


        //System.out.println(allmessage);
        Pattern pattern = Pattern.compile("<td class=\"fund-name-code\">                    <a href=\"http://fund.eastmoney.com/(.*?).html\" class=\"name\" title=\"(.*?)\">");
        Matcher matcher = pattern.matcher(allmessage);

            while (matcher.find()) {


                String biaozhuncha1= matcher.group(1);
                String biaozhuncha2= matcher.group(2);



                System.out.println(code+" "+ biaozhuncha1+" "+biaozhuncha2);
                FundCompany fundCompany=new FundCompany();
                fundCompany.setcompanyId(code);
                fundCompany.setFundId(biaozhuncha1);
                fundCompanyRepository.save(fundCompany);

               // bufferedWriter.write(code+" "+ biaozhuncha1+" "+biaozhuncha2+"\n");


            }




    }

}
