package reaper.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by max on 2017/9/9.
 */
public class RightNowMessage {

    public static ArrayList<Double> getSingleFundRightNowMessage(String fundCode) {
        // write your id here
        MyBug myBug = new MyBug();
        String allmessage = myBug.sendGet("http://fund.eastmoney.com/"+fundCode+".html?spm=001.2.swh", "");

        // System.out.println(allmessage);
        Pattern pattern = Pattern.compile("<span class=\"ui-font-large ui-color-(green|red) ui-num\" id=\"gz_gsz\">(.*?)</span>");

        Matcher matcher = pattern.matcher(allmessage);


        Double net_now = null;
        Double change = null;
        Double poportion=null;


        while (matcher.find()) {


            String code = matcher.group(2);

            net_now = Double.parseDouble(code);
            System.out.println(net_now);


        }

        Pattern pattern1 = Pattern.compile("<span class=\"ui-font-middle ui-color-(green|red) ui-num\" id=\"gz_gszze\">(.*?)</span>");
        Matcher matcher1 = pattern1.matcher(allmessage);
        while (matcher1.find()) {


            String code = matcher1.group(2);
            change = Double.parseDouble(code);
            System.out.println(change);


        }


        Pattern pattern2 = Pattern.compile("<span class=\"ui-font-middle ui-color-(green|red) ui-num\" id=\"gz_gszzl\">(.*?)%</span>");
        Matcher matcher2 = pattern2.matcher(allmessage);
        while (matcher2.find()) {


            String fundup = matcher2.group(2);
            //
            poportion = Double.parseDouble(fundup);
            System.out.println(poportion);


        }


            ArrayList<Double> arrayList=new ArrayList<Double>();
            arrayList.add(net_now);
            arrayList.add(change);
            arrayList.add(poportion);

            return arrayList;
    }
}
