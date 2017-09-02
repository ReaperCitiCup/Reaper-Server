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
public class IndustryRateTest {

    @Autowired
    IndustryRateRepository industryRateRepository;


    @Test
    public  void getFundShortMessageTest() {


        Code code=new Code();
        ArrayList<String> codes=code.getStockCode();


        int i=0;
        for (i=4970;i<codes.size();i++){



                getSingleFoundMessage(codes.get(i));

        }


    }

    public void getSingleFoundMessage(String code) {


        MyBug myBug = new MyBug();
        String allmessage = myBug.sendGet(
                "http://stock.finance.sina.com.cn/fundInfo/api/openapi.php/CaihuiFundInfoService.getIndustry?symbol="+code+"&callback=var%20Industry=","");


        System.out.println(allmessage);
        //System.out.println(decodeUnicode("\\u4fe1\\u606f\\u4f20\\u8f93\\u3001\\u8f6f\\u4ef6\\u548c\\u4fe1\\u606f\\u6280\\u672f\\u670d\\u52a1\\u4e1a"));
        Pattern pattern = Pattern.compile("\\{\"code\":\"(.*?)\",\"hymc\":\"(.*?)\",\"cyl\":\"(.*?)\",\"zjzb\":\"(.*?)\",\"hyltz\":(\"(.*?)\"|(.*?))\\}");
        Matcher matcher = pattern.matcher(allmessage);



        while (matcher.find()) {





                String mycode=matcher.group(1);
                String hymc=decodeUnicode(matcher.group(2));
                Double cyl=Double.parseDouble(matcher.group(3));
                Double zjzb=Double.parseDouble(matcher.group(4));
                String hyltzstr=matcher.group(5);
                if(hyltzstr.contains("\"")){
                    int end=hyltzstr.lastIndexOf("\"");
                    hyltzstr=hyltzstr.substring(1,end-1);

                }
                Double hyltz=Double.parseDouble(hyltzstr);


            IndustryRate industryRate=new IndustryRate();
            industryRate.setCode(code);
            industryRate.setIndustry(hymc);
            industryRate.setCyl(cyl);
            industryRate.setHyltz(hyltz);
            industryRate.setZjzb(zjzb);



                System.out.println(code+" "+mycode+" "+hymc+" "+cyl+" "+zjzb+" "+hyltz);
                industryRateRepository.save(industryRate);




        }




    }
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }


}
