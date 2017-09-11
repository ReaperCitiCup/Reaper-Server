package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.Company;
import reaper.model.FundNetValue;
import reaper.model.FundShortMessage;
import reaper.model.SpecialMessage;
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
public class FundNetValueTest {

    @Autowired
    FundNetValueRepository fundNetValueRepository;


    @Test
    public  void getFundShortMessageTest() {


        MissingCode code=new MissingCode();
        ArrayList<String> codes=code.getStockCode();


//        int i=0;
//        for (i=10;i<codes.size();i++){
//
//                System.out.println(codes.get(i));
//            getSingleFoundMessage(codes.get(i));
//        }
        getSingleFoundMessage("000335");
        getSingleFoundMessage("000310");
        getSingleFoundMessage("003273");
        getSingleFoundMessage("003274");
        getSingleFoundMessage("003637");
        getSingleFoundMessage("003638");


    }

    public void getSingleFoundMessage(String code) {


        MyBug myBug = new MyBug();
        String allmessage = myBug.sendPost("http://fund.eastmoney.com/f10/F10DataApi.aspx" , "type=lsjz&code="+code+"&page=1&per=100000");


        //System.out.println(allmessage);
        Pattern pattern = Pattern.compile("<tr><td>(.*?)</td><td class='tor bold'>(.*?)</td><td class='tor bold'>(.*?)</td><td class='tor bold (.*?)'>(.*?)%?</td><td>");
        Matcher matcher = pattern.matcher(allmessage);


            while (matcher.find()) {


                String datestr= matcher.group(1)+" ";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
                try {
                    Date date = sdf.parse(datestr);

                String biaozhuncha2= matcher.group(2);
                String biaozhuncha3= matcher.group(3);
                String xiapubilv1=matcher.group(5);

                FundNetValue fundNetValue=new FundNetValue();
                fundNetValue.setDate(date);
                fundNetValue.setCode(code);
                if(xiapubilv1.equals("")){
                    System.out.println("emptySTring");

                }
                else{

                   // System.out.println("夏普比率"+xiapubilv1);

                    fundNetValue.setDailyRate(Double.parseDouble(xiapubilv1));

                }
               // System.out.println(biaozhuncha2);
                if(biaozhuncha2.equals("")){

                }else {
                    fundNetValue.setUnitNetValue(Double.parseDouble(biaozhuncha2));
                }
                if(!(biaozhuncha3.equals(""))) {
                    fundNetValue.setCumulativeNetValue(Double.parseDouble(biaozhuncha3));
                }

                System.out.println(code+" "+datestr+" "+biaozhuncha2+" "+biaozhuncha3+" "+
                        xiapubilv1);
                fundNetValueRepository.save(fundNetValue);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }




    }


}
