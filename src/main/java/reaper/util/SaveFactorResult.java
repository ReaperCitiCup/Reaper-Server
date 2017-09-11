package reaper.util;

import org.junit.Test;
import reaper.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveFactorResult {
    public static void main(String[] args) throws IOException {
        new SaveFactorResult().saveFundAttribution();
    }

    /**
     * 读取
     */
    public List<BrisonResult> saveBrinsonResult() {
        File file = new File("src/main/brinson_result.csv");
        List<BrisonResult> results = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String s;
            while ((s = bf.readLine()) != null) {
                BrisonResult res = new BrisonResult();
                String attrs[] = s.split(",");
                while (attrs[0].length() < 6) {
                    attrs[0] = "0" + attrs[0];
                }
                res.setCode(attrs[0]);
                res.setAllocationEffect(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[1]));
                res.setSelectionEffect(attrs[2].endsWith("NaN") ? null : Double.valueOf(attrs[2]));
                res.setInteractionEffect(attrs[3].endsWith("NaN") ? null : Double.valueOf(attrs[3]));
                res.setActiveReturn(attrs[4].endsWith("NaN") ? null : Double.valueOf(attrs[4]));
                res.setGzExposure(attrs[5].endsWith("NaN") ? null : Double.valueOf(attrs[5]));
                res.setDfzfzExposure(attrs[6].endsWith("NaN") ? null : Double.valueOf(attrs[6]));
                res.setJrzExposure(attrs[7].endsWith("NaN") ? null : Double.valueOf(attrs[7]));
                res.setQyzExposure(attrs[8].endsWith("NaN") ? null : Double.valueOf(attrs[8]));
                res.setGszExposure(attrs[9].endsWith("NaN") ? null : Double.valueOf(attrs[9]));
                res.setZqpjExposure(attrs[10].endsWith("NaN") ? null : Double.valueOf(attrs[10]));
                res.setDqrzqExposure(attrs[11].endsWith("NaN") ? null : Double.valueOf(attrs[11]));
                res.setDxgjExposure(attrs[12].endsWith("NaN") ? null : Double.valueOf(attrs[12]));
                res.setOtherExposure(attrs[13].endsWith("NaN") ? null : Double.valueOf(attrs[13]));
                res.setGzReturn(attrs[14].endsWith("NaN") ? null : Double.valueOf(attrs[14]));
                res.setDfzfzReturn(attrs[15].endsWith("NaN") ? null : Double.valueOf(attrs[15]));
                res.setJrzReturn(attrs[16].endsWith("NaN") ? null : Double.valueOf(attrs[16]));
                res.setQyzReturn(attrs[17].endsWith("NaN") ? null : Double.valueOf(attrs[17]));
                res.setGszReturn(attrs[18].endsWith("NaN") ? null : Double.valueOf(attrs[18]));
                res.setZqpjReturn(attrs[19].endsWith("NaN") ? null : Double.valueOf(attrs[19]));
                res.setDqrzqReturn(attrs[20].endsWith("NaN") ? null : Double.valueOf(attrs[20]));
                res.setDxgjReturn(attrs[21].endsWith("NaN") ? null : Double.valueOf(attrs[21]));
                res.setOtherReturn(attrs[22].endsWith("NaN") ? null : Double.valueOf(attrs[22]));
                results.add(res);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 归因读取csv
     */
    public List<FactorResult> saveFactorResult(String type) {
        File file = new File("src/main/" + type + "factor_result.csv");
        List<FactorResult> results = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String s;
            while ((s = bf.readLine()) != null) {
                FactorResult res = new FactorResult();
                String attrs[] = s.split(",");

                while (attrs[0].length() < 6) {
                    attrs[0] = "0" + attrs[0];
                }
                res.setCode(attrs[0]);
                res.setBeta(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[1]));
                res.setBtop(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[2]));
                res.setEarningsYield(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[3]));
                res.setGrowth(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[4]));
                res.setLeverage(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[5]));
                res.setLiquidity(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[6]));
                res.setMomentum(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[7]));
                res.setNlsize(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[8]));
                res.setResidualvolatility(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[9]));
                res.setSize(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[10]));
                res.setJx(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[11]));
                res.setYh(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[12]));
                res.setFdc(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[13]));
                res.setYy(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[14]));
                res.setCyly(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[15]));
                res.setSmls(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[16]));
                res.setJc(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[17]));
                res.setJd(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[18]));
                res.setFzfz(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[19]));
                res.setSpyl(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[20]));
                res.setDzyqj(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[21]));
                res.setJtys(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[22]));
                res.setQc(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[23]));
                res.setQgzz(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[24]));
                res.setDljgysy(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[25]));
                res.setZh(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[26]));
                res.setTx(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[27]));
                res.setSysh(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[28]));
                res.setYsjs(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[29]));
                res.setNlmy(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[30]));
                res.setJz(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[31]));
                res.setJsj(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[32]));
                res.setJchg(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[33]));
                res.setMt(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[34]));
                res.setDlsb(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[35]));
                res.setGt(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[36]));
                res.setGfjg(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[37]));
                res.setFyhjr(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[38]));
                res.setCm(attrs[1].equals("NaN") ? null : Double.valueOf(attrs[39]));

                Double sum = 0.0;
                for (int i = 11; i <= 39; i++) {
                    if (Double.valueOf(attrs[i]).equals(Double.NaN)) {
                        sum = null;
                        break;
                    }
                    sum += Double.valueOf(attrs[i]);
                }
                res.setHyyzhj(sum);
                //type为空，则为非risk数据
                if (type == "") {
                    res.setMax1(attrs[1].equals("NaN") ? null :Double.valueOf(attrs[40]));
                    res.setMax2(attrs[1].equals("NaN") ? null :Double.valueOf(attrs[41]));
                    res.setFactorType('N');
                } else {
                    res.setFactorType('R');
                }
                results.add(res);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 基金指标
     */
    public List<FundScore> saveFundScore() throws IOException {
        File file = new File("src/main/基金指标数据.csv");
        List<FundScore> results = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String s;
        //第一行标题
        bf.readLine();
        while ((s = bf.readLine()) != null) {
            FundScore res = new FundScore();
            String attrs[] = s.split(",");

            while (attrs[0].length() < 6) {
                attrs[0] = "0" + attrs[0];
            }
            res.setCode(attrs[0]);
            res.setAnnualProfit(Integer.valueOf(attrs[2]));
            res.setResidualvolatility(Integer.valueOf(attrs[4]));
            res.setValueAtRisk(Integer.valueOf(attrs[6]));
            res.setStandardDeviation(Integer.valueOf(attrs[8]));
            res.setSharpeRiato(Integer.valueOf(attrs[10]));
            res.setBeta(Integer.valueOf(attrs[12]));
            res.setLatestAlpha(Integer.valueOf(attrs[14]));
            res.setTreynorRatio(Integer.valueOf(attrs[16]));
            res.setStockSelectAbility(Integer.valueOf(attrs[18]));
            res.setTimeSelectAbility(Integer.valueOf(attrs[20]));
            results.add(res);
        }

        return results;
    }

    /**
     * 基金指标
     */
    public List<FundAttribution> saveFundAttribution() throws IOException {
        File file = new File("src/main/基金指标数据.csv");
        List<FundAttribution> results = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String s;
        //第一行标题
        bf.readLine();
        while ((s = bf.readLine()) != null) {
            FundAttribution res = new FundAttribution();
            String attrs[] = s.split(",");

            while (attrs[0].length() < 6) {
                attrs[0] = "0" + attrs[0];
            }
            res.setCode(attrs[0]);
            res.setValueAtRisk(Double.valueOf(attrs[5]));
            res.setStandardDeviation(Double.valueOf(attrs[7]));
            res.setSharpeRiato(Double.valueOf(attrs[9]));
            res.setBeta(Double.valueOf(attrs[11]));
            res.setLatestAlpha(Double.valueOf(attrs[13]));
            res.setTreynorRatio(Double.valueOf(attrs[15]));
            res.setStockSelectAbility(Double.valueOf(attrs[17]));
            res.setTimeSelectAbility(Double.valueOf(attrs[19]));
            results.add(res);
        }

        return results;
    }

    public List<FundRank> saveFundRank() throws IOException {
        File file = new File("src/main/rank_data.csv");
        List<FundRank> results = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String s;
        while ((s = bf.readLine()) != null) {
            FundRank res = new FundRank();
            String attrs[] = s.split(",");

            while (attrs[0].length() < 6) {
                attrs[0] = "0" + attrs[0];
            }
            res.setCode(attrs[0]);
            res.setRank1(Double.valueOf(attrs[1]));
            res.setRank2(Double.valueOf(attrs[2]));
            res.setRank3(Double.valueOf(attrs[3]));
            res.setRank4(Double.valueOf(attrs[4]));
            res.setRank5(Double.valueOf(attrs[5]));
            res.setRank6(Double.valueOf(attrs[6]));
            res.setRank7(Double.valueOf(attrs[7]));
            res.setRank8(Double.valueOf(attrs[8]));
            res.setRank9(Double.valueOf(attrs[9]));
            res.setRank10(Double.valueOf(attrs[10]));
            results.add(res);
        }
        return results;
    }


}
