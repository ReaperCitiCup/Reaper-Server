package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.BasicStockIndex;
import reaper.model.ManagerEdge;
import reaper.repository.BasicStockIndexRepository;
import reaper.repository.CompanyRepository;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by max on 2017/9/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class BaiscResultRepositoryTest {




    @Autowired
    BasicStockIndexRepository basicStockIndexRepository;



    @Test
    public void solve(){

        //getAllIn("000001","上证指数");
//        getAllIn("000010","上证180");
//        getAllIn("000016","上证50");
//        getAllIn("399900","沪深300");
//        getAllIn("399905","中正500");
        getAllIn("000012","国债指数");
    }


    public void getAllIn(String code,String name){


        Date date;
        String stockID="";
        String stockName="";
        Double closePrice = null;
        Double highPrice= null;
        Double lowPrice= null;
        Double openPrice= null;
        Double beforeClosePrice = null;
        Double fluctuation= null;
        Double priceFluctuation= null;
        Double volume= null;
        Double transactionAmount= null;


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");








        File file=new File(code+".csv");

        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s=null;
            bufferedReader.readLine();

            while ((s=bufferedReader.readLine())!=null){

                //System.out.println(s);

                String[] temp = s.split(",");

                date=sdf.parse(temp[0]);
                if(!temp[1].contains("None")){
                    stockID=temp[1];
                }
               stockName=name;

                if(!temp[3].contains("None")){
                    closePrice=Double.parseDouble(temp[3]);
                }
                if(!temp[4].contains("None")){
                    highPrice=Double.parseDouble(temp[4]);
                }
                if(!temp[5].contains("None")){
                    lowPrice=Double.parseDouble(temp[5]);
                }
                if(!temp[6].contains("None")){
                    openPrice=Double.parseDouble(temp[6]);
                }

                if(!temp[7].contains("None")) {
                    beforeClosePrice = Double.parseDouble(temp[7]);
                }
                if(!temp[8].contains("None")) {
                    fluctuation = Double.parseDouble(temp[8]);
                }
                if(!temp[9].contains("None")) {
                    priceFluctuation = Double.parseDouble(temp[9]);
                }
                if(!temp[10].contains("None")) {
                     volume= Double.parseDouble(temp[10]);
                }
                if(!temp[11].contains("None")) {
                   transactionAmount = Double.parseDouble(temp[11]);
                }

                String iso = new String(stockName.getBytes("UTF-8"),"ISO-8859-1");
                String utf8=new String(iso.getBytes("ISO-8859-1"),"UTF-8");

                BasicStockIndex basicStockIndex=new BasicStockIndex(code,utf8);
                basicStockIndex.setDate(date);
                basicStockIndex.setBeforeClosePrice(beforeClosePrice);
                basicStockIndex.setClosePrice(closePrice);
                basicStockIndex.setFluctuation(fluctuation);
                basicStockIndex.setHighPrice(highPrice);
                basicStockIndex.setLowPrice(lowPrice);
                basicStockIndex.setOpenPrice(openPrice);
                basicStockIndex.setPriceFluctuation(priceFluctuation);
                basicStockIndex.setVolume(volume);
                basicStockIndex.setTransactionAmount(transactionAmount);
                basicStockIndexRepository.save(basicStockIndex);
                System.out.println(basicStockIndex.getDate()+basicStockIndex.getStockName());





            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }








}
