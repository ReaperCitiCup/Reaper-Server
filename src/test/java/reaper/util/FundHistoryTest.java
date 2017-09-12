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
public class FundHistoryTest {

    @Autowired
    FundHistoryRepository fundHistoryRepository;


    @Test
    public  void getFundShortMessageTest() {



        Code code=new Code();
        ArrayList<String> arrayList=code.getStockCode();


        int i=0;
        for(i=785;i<arrayList.size();i++) {
            getStockManagerHistory(arrayList.get(i));

        }
//etStockManagerHistory("000067");

    }
    public void getStockManagerHistory(String mycode){
        int i;

        MyBug myBug=new MyBug();
        //String allmessage=myBug.sendGet("http://fund.eastmoney.com/manager/"+mycode+".html","");
        String allmessage=myBug.sendGet("http://fund.eastmoney.com/manager/"+mycode+".html","");



        // System.out.println(allmessage);
        Pattern pattern = Pattern.compile("<a class=\"\" href=\"http://fund.eastmoney.com/(.*?).html\">(.*?)</a>" +
                "</td><td class=\"tdl\"><a class=\"\" href=\"http://fund.eastmoney.com/(.*?).html\">(.*?)</a></td>" +
                "<td class=\"xq\"><a class=\"\" href=\"http://fund.eastmoney.com/(.*?).html\">估值图" +
                "</a><a href='http://guba.eastmoney.com/list,of(.*?).html'>基金吧</a><a href='http://fund.eastmoney.com/f10/(.*?).html'>" +
                "档案</a></td><td>(.*?)</td><td>(<span class='.*?'>(.*?)</span><span class='.*?'></span>|--)</td><td>(.*?)</td><td>(.*?)</td>" +
                "<td class=\"(.*?)\">(-|(.*?)%)</td></tr>");
        Matcher matcher = pattern.matcher(allmessage);


            while (matcher.find()) {


                String code = matcher.group(3);
                String name=matcher.group(4);
                String name1=matcher.group(8);
                String name2=matcher.group(10);
                String name3=matcher.group(11);
                String name4=matcher.group(12);
                String name5=matcher.group(14);


                String managerId=mycode;

                String fundCode=code;

                String fundName=name;

                String fundType1=name1;

                String fundType2;
                Double size = null;
                if(name2!=null) {
                    size=Double.parseDouble(name2);
                }
                String alltime[]=name3.split("~");
                String startDatestr=alltime[0];
                String endDatstr=alltime[1];


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date startDate=null;
                Date endDate=null;
                try {
                    startDate=sdf.parse(startDatestr);
                    if(!endDatstr.contains("至今")) {

                        endDate = sdf.parse(endDatstr);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String time=name4;


                String timestr=name5;

                Double payback=null;
                int loc=name5.indexOf("%");
                if(loc!=-1) {
                    timestr = timestr.substring(0, loc);
                    payback=Double.parseDouble(timestr);
                }
                FundHistory fundHistory=new FundHistory();
                fundHistory.setManagerId(managerId);
                fundHistory.setEndDate(endDate);
                fundHistory.setFundCode(fundCode);
                fundHistory.setTime(time);
                fundHistory.setPayback(payback);
                fundHistory.setStartDate(startDate);

                fundHistoryRepository.save(fundHistory);






                System.out.println("经理代号"+managerId+" "+fundCode+" "+fundName+" "+fundType1+" "+size+" "+startDate+" "+endDate+" "+time+" "+payback+"\n");
                //  bufferedWriter.write("经理代号"+mycode+"\t"+id+"\t"+name+"\t"+name1+"\t"+name2+"\t"+name3+"\t"+name4+"\t"+name5+"\n"
                //  );





            }




    }
}
