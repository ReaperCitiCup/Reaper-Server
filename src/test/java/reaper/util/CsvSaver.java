package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.*;
import reaper.repository.*;

import java.io.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class CsvSaver {
    @Autowired
    FactorResultRepository factorResultRepository;

    @Autowired
    BrisonResultRepository brisonResultRepository;

    @Autowired
    FundRepository fundRepository;

    @Autowired
    FundScoreRepository fundScoreRepository;

    @Autowired
    FundAttributionRepository fundAttributionRepository;

    @Autowired
    FundManagerRepository fundManagerRepository;

    @Autowired
    ManagerRemarkRepository managerRemarkRepository;

    @Autowired
    FundRankRepository fundRankRepository;

    @Autowired
    CPRRepository cprRepository;

    SaveFactorResult saveFactorResult = new SaveFactorResult();

    @Test
    public void saveFactorResult(){
        for(FactorResult factorResult:saveFactorResult.saveFactorResult("risk_")){
            System.out.println(factorResult);
            factorResultRepository.save(factorResult);
        }
    }

    @Test
    public void saveBrisonResult(){
        for(BrisonResult brisonResult:saveFactorResult.saveBrinsonResult()){
            System.out.println(brisonResult);
            brisonResultRepository.save(brisonResult);
        }
    }

     @Test
    public void updateFundInfo() throws Exception{
         File file = new File("基金指标数据.csv");
         BufferedReader br = new BufferedReader(new FileReader(file));
         String s;
         //第一行标题
         s = br.readLine();
         while ((s=br.readLine())!=null){
             String attrs[] = s.split(",");
             while (attrs[0].length()<6){
                 attrs[0] = "0"+attrs[0];
             }
             Fund fund = fundRepository.findByCode(attrs[0]);

             fund.setAnnualProfit(Double.valueOf(attrs[1])*100);
             fund.setVolatility(Double.valueOf(attrs[3])*100);
             System.out.println(fund);
             fundRepository.save(fund);
         }
    }

    @Test
    public void saveFundScore() throws IOException {
        for(FundScore fundScore:saveFactorResult.saveFundScore()){
            fundScoreRepository.save(fundScore);
        }
    }

    @Test
    public void saveFundAttribution() throws IOException {
        for(FundAttribution fundAttribution:saveFactorResult.saveFundAttribution()){
            System.out.println(fundAttribution);
            fundAttributionRepository.save(fundAttribution);
        }
    }

    @Test
    public void updateManagerAvg(){
        for(FundScore fundScore:fundScoreRepository.findAll()) {
            System.out.println(fundScore.getCode());
            try {
                Double sum = 0.0;
                int count = 0;
                for (FundManager fundManager : fundManagerRepository.findByFundCode(fundScore.getCode())) {
                    count++;
                    sum+=managerRemarkRepository.findByManagerId(Integer.valueOf(fundManager.getManagerId())).getAverageAbility();
                }
                fundScore.setManagerAvg(sum/count);
                if(count==0){
                    fundScore.setManagerAvg(null);
                }
                fundScoreRepository.save(fundScore);
            }catch (NullPointerException e){
                System.out.println("error:"+fundScore.getCode());
                e.printStackTrace();
            }
        }
    }

    @Test
    public void updateCPR() throws IOException {
        File file = new File("src/main/CPR.csv");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        while ((s=br.readLine())!=null) {
            String attrs[] = s.split(",");
            while (attrs[0].length() < 6) {
                attrs[0] = "0" + attrs[0];
            }
            FundScore fundScore = fundScoreRepository.findByCode(attrs[0]);
            System.out.println(attrs[0]+" "+attrs[6]);
            if((fundScore!=null)&&(fundScore.getCpr()==null)) {
                fundScore.setCpr(Integer.valueOf(attrs[6]));
                fundScoreRepository.save(fundScore);
            }
        }
    }

    @Test
    public void updateMax12() throws IOException {
        File file = new File("src/main/factor_result.csv");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String s;
        while ((s = bf.readLine()) != null) {
            String attrs[] = s.split(",");

            while (attrs[0].length() < 6) {
                attrs[0] = "0" + attrs[0];
            }
            System.out.println(attrs[0]);
            System.out.println(attrs[40]+" "+attrs[41]);

            FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(attrs[0],'N');
            factorResult.setMax1(Double.valueOf(attrs[40]));
            factorResult.setMax2(Double.valueOf(attrs[41]));
            factorResultRepository.save(factorResult);
        }
    }

    @Test
    public void saveFundRank() throws IOException {
        for(FundRank fundRank:saveFactorResult.saveFundRank()){
            fundRankRepository.save(fundRank);
        }
    }

    @Test
    public void fillCPRCode()throws Exception{
        for(CPR cpr:cprRepository.findAll()){
            while (cpr.getFundId().length()<6){
                cpr.setFundId("0"+cpr.getFundId());
            }
            cprRepository.save(cpr);
        }
    }

    @Test
    public void saveFandRankByType() throws IOException {
        File file = new File("src/main/factor_result.csv");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String s;
        while ((s = bf.readLine()) != null) {
            String attrs[] = s.split(",");

            while (attrs[0].length() < 6) {
                attrs[0] = "0" + attrs[0];
            }

            
        }
    }
}
