package reaper.util;

import reaper.model.FactorResult;

import java.io.*;

public class SaveFactorResult {
    public static void main(String[] args) {
        File file = new File("src/main/factor_result.csv");
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String s;
            while ((s = bf.readLine())!=null){
                FactorResult res = new FactorResult();
                String attrs[] = s.split(",");

                while (attrs[0].length()<6){
                    attrs[0] = "0"+attrs[0];
                }
                res.setCode(attrs[0]);
                res.setBeta(Double.valueOf(attrs[1]));
                res.setBtop(Double.valueOf(attrs[2]));
                res.setEarningsYield(Double.valueOf(attrs[3]));
                res.setGrowth(Double.valueOf(attrs[4]));
                res.setLeverage(Double.valueOf(attrs[5]));
                res.setLiquidity(Double.valueOf(attrs[6]));
                res.setMomentum(Double.valueOf(attrs[7]));
                res.setNlsize(Double.valueOf(attrs[8]));
                res.setResidualvolatility(Double.valueOf(attrs[9]));
                res.setSize(Double.valueOf(attrs[10]));
                res.setJx(Double.valueOf(attrs[11]));
                res.setYh(Double.valueOf(attrs[12]));
                res.setFdc(Double.valueOf(attrs[13]));
                res.setYy(Double.valueOf(attrs[14]));
                res.setCyly(Double.valueOf(attrs[15]));
                res.setSmls(Double.valueOf(attrs[16]));
                res.setJc(Double.valueOf(attrs[17]));
                res.setJd(Double.valueOf(attrs[18]));
                res.setFzfz(Double.valueOf(attrs[19]));
                res.setSpyl(Double.valueOf(attrs[20]));
                res.setDzyqj(Double.valueOf(attrs[21]));
                res.setJtys(Double.valueOf(attrs[22]));
                res.setQc(Double.valueOf(attrs[23]));
                res.setQgzz(Double.valueOf(attrs[24]));
                res.setDljgysy(Double.valueOf(attrs[25]));
                res.setZh(Double.valueOf(attrs[26]));
                res.setTx(Double.valueOf(attrs[27]));
                res.setSysh(Double.valueOf(attrs[28]));
                res.setYsjs(Double.valueOf(attrs[29]));
                res.setNlmy(Double.valueOf(attrs[30]));
                res.setJz(Double.valueOf(attrs[31]));
                res.setJsj(Double.valueOf(attrs[32]));
                res.setJchg(Double.valueOf(attrs[33]));
                res.setMt(Double.valueOf(attrs[34]));
                res.setDlsb(Double.valueOf(attrs[35]));
                res.setGt(Double.valueOf(attrs[36]));
                res.setGfjg(Double.valueOf(attrs[37]));
                res.setFyhjr(Double.valueOf(attrs[38]));
                res.setCm(Double.valueOf(attrs[39]));
                res.setHyyzhj(Double.valueOf(attrs[40]));
                System.out.println(res);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
