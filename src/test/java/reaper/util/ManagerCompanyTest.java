package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.*;
import reaper.repository.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by max on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ManagerCompanyTest {

    @Autowired
    ManagerCompanyRespository managerCompanyRespository;


    @Test
    public  void getFundShortMessageTest() {


        File file=new File("reaper_temp.csv");
        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s=null;
            while ((s=bufferedReader.readLine())!=null){
                System.out.println(s);
                String temp[]=s.split(",");
                String companyid=temp[0];
                String managerid=temp[1];
                ManagerCompany managerCompany=new ManagerCompany();
                managerCompany.setCompanyId(companyid);
                managerCompany.setManagerId(managerid);
                managerCompanyRespository.save(managerCompany);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    }


