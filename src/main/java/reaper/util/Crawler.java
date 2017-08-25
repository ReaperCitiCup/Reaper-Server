package reaper.util;

import reaper.model.Fund;
import reaper.model.FundHoldBond;
import reaper.model.FundHoldStock;
import reaper.repository.FundHoldBondRepository;
import reaper.repository.FundHoldStockRepository;
import reaper.repository.FundRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬取FundHoldStock信息
 */
public class Crawler {
    public static void main(String[] args) {
        Code code = new Code();
        Crawler crawler = new Crawler();
        for (String c : code.getStockCode()) {
            crawler.crawlFundDetail(c, null);
        }
    }

    /**
     * 根据年份和基金代码爬取持有股份的信息
     *
     * @param fundCode 基金代码
     * @param year     年份
     */
    public void crawlFondHoldStock(String fundCode, String year, FundHoldStockRepository fundHoldStockRepository) {
        String result = getHtml("http://fund.eastmoney.com/f10/FundArchivesDatas.aspx?type=jjcc&code=" + fundCode + "&topline=100&year=" + year);

//        result = "<td><a href='http://quote.eastmoney.com/sz300335.html'>300335</a></td>";
//        System.out.println(result);
        //分割季度的pattern
        Pattern seasonPattern = Pattern.compile("&nbsp;&nbsp;(\\d{4})年(\\d){1}季度股票投资明细");
        Matcher seasonMatcher = seasonPattern.matcher(result);
        String[] seasonNumbers = new String[4];
        int count = 0;
        while (seasonMatcher.find()) {
            seasonNumbers[count] = seasonMatcher.group(2);
            count++;
        }

        String[] seasons = seasonPattern.split(result);

        Pattern codePattern = Pattern.compile("<td><a href='[^']*'>(\\d{5,6})</a></td>");
        Pattern ratePattern = Pattern.compile("<a href='(.)*?'>档案</a></td><td class='tor'>((((\\d|\\.)+)%)|---)?</td>");
        //分割后，0为抬头，后面对应各个季度
        for (int i = 0; i < count; i++) {
            Matcher codeMatcher = codePattern.matcher(seasons[i + 1]);
            Matcher rateMatcher = ratePattern.matcher(seasons[i + 1]);
            while (codeMatcher.find()) {
                rateMatcher.find();
                FundHoldStock fundHoldStock = new FundHoldStock(fundCode, Integer.valueOf(year), Integer.valueOf(seasonNumbers[i]), codeMatcher.group(1), rateMatcher.group(4) == null ? null : Double.valueOf(rateMatcher.group(4)));
                fundHoldStockRepository.save(fundHoldStock);

//                System.out.println(fundHoldStock);
//                System.out.println(fundCode + " " + year + " " + seasonNumbers[i] + " " + codeMatcher.group(1) + " " + rateMatcher.group(4));
            }
        }
    }

    public void crawlFundHoldBond(String fundCode, String year, FundHoldBondRepository fundHoldBondRepository) {
        System.out.println(fundCode + " " + year);
        String result = getHtml("http://fund.eastmoney.com/f10/FundArchivesDatas.aspx?type=zqcc&code=" + fundCode + "&topline=100&year=" + year);

//        System.out.println(result);
        //分割季度的pattern
        Pattern seasonPattern = Pattern.compile("&nbsp;&nbsp;(\\d{4})年(\\d){1}季度债券投资明细");
        Matcher seasonMatcher = seasonPattern.matcher(result);
        String[] seasonNumbers = new String[4];
        int count = 0;
        while (seasonMatcher.find()) {
            seasonNumbers[count] = seasonMatcher.group(2);
            count++;
        }

        String[] seasons = seasonPattern.split(result);

        Pattern pattern = Pattern.compile("<td>(\\d{6,7})</td><td class='tol'>(.*?)</td><td class='tor'>((((\\d|\\.)+)%)|---)?</td>");
        //分割后，0为抬头，后面对应各个季度
        for (int i = 0; i < count; i++) {
            Matcher matcher = pattern.matcher(seasons[i + 1]);
            while (matcher.find()) {
                FundHoldBond fundHoldBond = new FundHoldBond(fundCode, Integer.valueOf(year), Integer.valueOf(seasonNumbers[i]), matcher.group(1), matcher.group(2), matcher.group(5) == null ? null : Double.valueOf(matcher.group(5)));
                fundHoldBondRepository.save(fundHoldBond);

//                System.out.println(fundHoldBond);
//                System.out.println(fundCode + " " + year + " " + seasonNumbers[i] + " " + codeMatcher.group(1) + " " + rateMatcher.group(4));
            }
        }
    }

    public void crawlFundDetail(String fundCode, FundRepository fundRepository) {
        System.out.println(fundCode);
        Fund fund = new Fund();
        fund.setFundCode(fundCode);

        String res = getHtml("http://fund.eastmoney.com/" + fundCode + ".html");

        //name
        Pattern pattern = Pattern.compile("<div style=\"float: left\">(.+?)<span>");
        Matcher matcher = pattern.matcher(res);
        matcher.find();
        fund.setName(matcher.group(1));

        //type
        pattern = Pattern.compile("<td>基金类型：(<a href=\"(.*?)\">)?(.*?)(</a>)?(&nbsp;&nbsp;)?(\\|&nbsp;&nbsp;(.*?))?</td>");
        matcher = pattern.matcher(res);
        matcher.find();
        fund.setType1(matcher.group(3));
        fund.setType2(matcher.group(7));

        //date
        pattern = Pattern.compile("<td><span class=\"letterSpace01\">成 立 日</span>：(.*?)</td>");
        matcher = pattern.matcher(res);
        matcher.find();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fund.setEstablishmentDate(sdf.parse(matcher.group(1)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //scope
        pattern = Pattern.compile("基金规模</a>：(.*?)亿元");
        matcher = pattern.matcher(res);
        matcher.find();
        fund.setScope(matcher.group(1).equals("--")?null:Double.valueOf(matcher.group(1)));

//        System.out.println(fund);
        fundRepository.save(fund);
    }

    private String getHtml(String url) {
        // 定义一个字符串用来存储网页内容
        String result = "";
        // 定义一个缓冲字符输入流
        BufferedReader in = null;
        try {
            // 将string转成url对象
            URL realUrl = new URL(url);
            // 初始化一个链接到那个url的连接
            URLConnection connection = realUrl.openConnection();
            // 开始实际的连接
            connection.connect();
            // 初始化 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null) {
                // 遍历抓取到的每一行并将其存储到result里面
                result += line + "\n";
            }
        } catch (Exception e) {
            System.out.println(url);
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            return getHtml(url);
        } // 使用finally来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
