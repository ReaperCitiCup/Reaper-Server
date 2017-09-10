package reaper.util;

import reaper.model.*;
import reaper.repository.*;

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
        for(int i=1;i<=123;i++)
            crawler.crawlTotalPortion(String.valueOf(i),null);
    }

    /**
     * 根据年份和基金代码爬取持有股份的信息
     *
     * @param fundCode 基金代码
     * @param year     年份
     */
    public void crawlFondHoldStock(String fundCode, String year, FundHoldStockRepository fundHoldStockRepository) {
        System.out.println(fundCode + " " + year);
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
        Pattern ratePattern = Pattern.compile("<td class='tor'>((((\\d|\\.)+)%)|---)?</td>");
        //分割后，0为抬头，后面对应各个季度
        for (int i = 0; i < count; i++) {
            Matcher codeMatcher = codePattern.matcher(seasons[i + 1]);
            Matcher rateMatcher = ratePattern.matcher(seasons[i + 1]);
            while (codeMatcher.find()) {
                rateMatcher.find();
                FundHoldStock fundHoldStock = new FundHoldStock(fundCode, Integer.valueOf(year), Integer.valueOf(seasonNumbers[i]), codeMatcher.group(1), rateMatcher.group(3) == null ? null : Double.valueOf(rateMatcher.group(3)));
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
        fund.setCode(fundCode);

        String res = getHtml("http://fund.eastmoney.com/" + fundCode + ".html?spm=search");

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

    public void crawlManager(String managerId, ManagerRepository managerRepository){
        Manager manager = new Manager();
        manager.setManagerId(managerId);

        String res = getHtml("http://fund.eastmoney.com/manager/"+managerId+".html");
        System.out.println(managerId);
//        System.out.println(res);

        //name
        Pattern pattern = Pattern.compile("<h3 id=\"name_1\" title=\"(.*?)\">");
        Matcher matcher = pattern.matcher(res);
        matcher.find();
        manager.setName(matcher.group(1));
//        System.out.println(manager.getName());

        //date
        pattern = Pattern.compile("<span>任职起始日期：</span>(\\d{4}-\\d{2}-\\d{2})");
        matcher = pattern.matcher(res);
        matcher.find();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            manager.setAppointedDate(sdf.parse(matcher.group(1)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(manager.getAppointedDate());

        //introduction
        pattern = Pattern.compile("<span class=\"strong\">基金经理简介：</span>(.*?)\n");
        matcher = pattern.matcher(res);
        matcher.find();
        manager.setIntroduction(matcher.group(1));
//        System.out.println(manager.getIntroduction());

        //totalScope
        pattern = Pattern.compile("<span class='redText'>(\\d+\\.\\d+)</span>");
        matcher = pattern.matcher(res);
        matcher.find();
        manager.setTotalScope(Double.valueOf(matcher.group(1)));
//        System.out.println(manager.getTotalScope());

        //bestReturn
        pattern = Pattern.compile("<span class='(red|green|ping)Text'>(-?\\d+\\.\\d+)%</span>");
        matcher = pattern.matcher(res);
        matcher.find();
        manager.setBestReturns(Double.valueOf(matcher.group(2)));
//        System.out.println(manager.getBestReturns());

//        System.out.println(manager);
        managerRepository.save(manager);
    }

    public void crawlAssetAllocation(String code, AssetAllocationRepository assetAllocationRepository){
        AssetAllocation assetAllocation = new AssetAllocation();
        assetAllocation.setCode(code);
        System.out.println(code);

        String res = getHtml("http://stock.finance.sina.com.cn/fundInfo/api/openapi.php/CaihuiFundInfoService.getFullAssetAL?symbol="+code+"&format=json&callback=var%20LatestAssetAL=");
//        System.out.println(res);

        Pattern pattern = Pattern.compile("\"gpjzb\":(.*?),\"yhckjzb\":(.*?),\"zqjzb\":(.*?)}");
        Matcher matcher = pattern.matcher(res);

        if(matcher.find()) {
            assetAllocation.setStock(Double.valueOf(matcher.group(1)));
            assetAllocation.setBank(Double.valueOf(matcher.group(2)));
            assetAllocation.setBond(Double.valueOf(matcher.group(3)));
        }
        else {
            pattern = Pattern.compile("\"zqjzb\":\"(.*?)\"");
            matcher = pattern.matcher(res);
            if(matcher.find()) {
                assetAllocation.setBond(Double.valueOf(matcher.group(1)));
                assetAllocation.setBank(0.0);
                assetAllocation.setStock(0.0);
            }else {
                System.out.println(code+" 无数据！！！！");
            }
        }
//        System.out.println(assetAllocation);
        assetAllocationRepository.save(assetAllocation);
    }

    public void crawlTotalPortion(String page,TotalPortionRepository totalPortionRepository){
        String url = "http://fund.eastmoney.com/data/FundDataPortfolio_Interface.aspx?dt=8&t=2017_2&pi="+page+"&pn=50&mc=returnJson&st=asc&sc=bzdm";
        String res = getHtml(url);

        Pattern pattern = Pattern.compile("\\[\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\"]");
        Matcher matcher = pattern.matcher(res);

        TotalPortion totalPortion = new TotalPortion();

        while (matcher.find()){
            totalPortion.setCode(matcher.group(1));
            totalPortion.setValue(Double.valueOf(matcher.group(5).replace(",","")));
            System.out.println(totalPortion);
            totalPortionRepository.save(totalPortion);
        }

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
