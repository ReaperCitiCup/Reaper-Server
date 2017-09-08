package reaper.util;

import reaper.model.BrisonResult;
import reaper.model.FactorResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveFactorResult {
    public static void main(String[] args) {
        new SaveFactorResult().saveBrinsonResult();
    }

    /**
     * 读取
     */
    public List<BrisonResult> saveBrinsonResult(){
        File file = new File("src/main/brinson_result.csv");
        List<BrisonResult> results = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String s;
            while ((s = bf.readLine()) != null) {
                BrisonResult res = new BrisonResult();
                String attrs[] = s.split(",");
                while (attrs[0].length()<6){
                    attrs[0] = "0"+attrs[0];
                }
                res.setCode(attrs[0]);
                res.setAllocationEffect(attrs[1].equals("NaN")?null:Double.valueOf(attrs[1]));
                res.setSelectionEffect(attrs[2].endsWith("NaN")?null:Double.valueOf(attrs[2]));
                res.setInteractionEffect(attrs[3].endsWith("NaN")?null:Double.valueOf(attrs[3]));
                res.setActiveReturn(attrs[4].endsWith("NaN")?null:Double.valueOf(attrs[4]));
                res.setGzExposure(attrs[5].endsWith("NaN")?null:Double.valueOf(attrs[5]));
                res.setDfzfzExposure(attrs[6].endsWith("NaN")?null:Double.valueOf(attrs[6]));
                res.setJrzExposure(attrs[7].endsWith("NaN")?null:Double.valueOf(attrs[7]));
                res.setQyzExposure(attrs[8].endsWith("NaN")?null:Double.valueOf(attrs[8]));
                res.setGszExposure(attrs[9].endsWith("NaN")?null:Double.valueOf(attrs[9]));
                res.setZqpjExposure(attrs[10].endsWith("NaN")?null:Double.valueOf(attrs[10]));
                res.setDqrzqExposure(attrs[11].endsWith("NaN")?null:Double.valueOf(attrs[11]));
                res.setDxgjExposure(attrs[12].endsWith("NaN")?null:Double.valueOf(attrs[12]));
                res.setOtherExposure(attrs[13].endsWith("NaN")?null:Double.valueOf(attrs[13]));
                res.setGzReturn(attrs[14].endsWith("NaN")?null:Double.valueOf(attrs[14]));
                res.setDfzfzReturn(attrs[15].endsWith("NaN")?null:Double.valueOf(attrs[15]));
                res.setJrzReturn(attrs[16].endsWith("NaN")?null:Double.valueOf(attrs[16]));
                res.setQyzReturn(attrs[17].endsWith("NaN")?null:Double.valueOf(attrs[17]));
                res.setGszReturn(attrs[18].endsWith("NaN")?null:Double.valueOf(attrs[18]));
                res.setZqpjReturn(attrs[19].endsWith("NaN")?null:Double.valueOf(attrs[19]));
                res.setDqrzqReturn(attrs[20].endsWith("NaN")?null:Double.valueOf(attrs[20]));
                res.setDxgjReturn(attrs[21].endsWith("NaN")?null:Double.valueOf(attrs[21]));
                res.setOtherReturn(attrs[22].endsWith("NaN")?null:Double.valueOf(attrs[22]));
                results.add(res);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 归因读取csv
     */
    public List<FactorResult> saveFactorResult(String type){
        File file = new File("src/main/"+type+"factor_result.csv");
        List<FactorResult> results = new ArrayList<>();
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
                res.setBeta(attrs[1].equals("NaN")?null:Double.valueOf(attrs[1]));
                res.setBtop(attrs[1].equals("NaN")?null:Double.valueOf(attrs[2]));
                res.setEarningsYield(attrs[1].equals("NaN")?null:Double.valueOf(attrs[3]));
                res.setGrowth(attrs[1].equals("NaN")?null:Double.valueOf(attrs[4]));
                res.setLeverage(attrs[1].equals("NaN")?null:Double.valueOf(attrs[5]));
                res.setLiquidity(attrs[1].equals("NaN")?null:Double.valueOf(attrs[6]));
                res.setMomentum(attrs[1].equals("NaN")?null:Double.valueOf(attrs[7]));
                res.setNlsize(attrs[1].equals("NaN")?null:Double.valueOf(attrs[8]));
                res.setResidualvolatility(attrs[1].equals("NaN")?null:Double.valueOf(attrs[9]));
                res.setSize(attrs[1].equals("NaN")?null:Double.valueOf(attrs[10]));
                res.setJx(attrs[1].equals("NaN")?null:Double.valueOf(attrs[11]));
                res.setYh(attrs[1].equals("NaN")?null:Double.valueOf(attrs[12]));
                res.setFdc(attrs[1].equals("NaN")?null:Double.valueOf(attrs[13]));
                res.setYy(attrs[1].equals("NaN")?null:Double.valueOf(attrs[14]));
                res.setCyly(attrs[1].equals("NaN")?null:Double.valueOf(attrs[15]));
                res.setSmls(attrs[1].equals("NaN")?null:Double.valueOf(attrs[16]));
                res.setJc(attrs[1].equals("NaN")?null:Double.valueOf(attrs[17]));
                res.setJd(attrs[1].equals("NaN")?null:Double.valueOf(attrs[18]));
                res.setFzfz(attrs[1].equals("NaN")?null:Double.valueOf(attrs[19]));
                res.setSpyl(attrs[1].equals("NaN")?null:Double.valueOf(attrs[20]));
                res.setDzyqj(attrs[1].equals("NaN")?null:Double.valueOf(attrs[21]));
                res.setJtys(attrs[1].equals("NaN")?null:Double.valueOf(attrs[22]));
                res.setQc(attrs[1].equals("NaN")?null:Double.valueOf(attrs[23]));
                res.setQgzz(attrs[1].equals("NaN")?null:Double.valueOf(attrs[24]));
                res.setDljgysy(attrs[1].equals("NaN")?null:Double.valueOf(attrs[25]));
                res.setZh(attrs[1].equals("NaN")?null:Double.valueOf(attrs[26]));
                res.setTx(attrs[1].equals("NaN")?null:Double.valueOf(attrs[27]));
                res.setSysh(attrs[1].equals("NaN")?null:Double.valueOf(attrs[28]));
                res.setYsjs(attrs[1].equals("NaN")?null:Double.valueOf(attrs[29]));
                res.setNlmy(attrs[1].equals("NaN")?null:Double.valueOf(attrs[30]));
                res.setJz(attrs[1].equals("NaN")?null:Double.valueOf(attrs[31]));
                res.setJsj(attrs[1].equals("NaN")?null:Double.valueOf(attrs[32]));
                res.setJchg(attrs[1].equals("NaN")?null:Double.valueOf(attrs[33]));
                res.setMt(attrs[1].equals("NaN")?null:Double.valueOf(attrs[34]));
                res.setDlsb(attrs[1].equals("NaN")?null:Double.valueOf(attrs[35]));
                res.setGt(attrs[1].equals("NaN")?null:Double.valueOf(attrs[36]));
                res.setGfjg(attrs[1].equals("NaN")?null:Double.valueOf(attrs[37]));
                res.setFyhjr(attrs[1].equals("NaN")?null:Double.valueOf(attrs[38]));
                res.setCm(attrs[1].equals("NaN")?null:Double.valueOf(attrs[39]));
                Double sum = 0.0;
                for(int i=11;i<=39;i++){
                    if (Double.valueOf(attrs[i]).equals(Double.NaN)){
                        sum = null;
                        break;
                    }
                    sum+=Double.valueOf(attrs[i]);
                }
                res.setHyyzhj(sum);
                //type为空，则为非risk数据
                if(type==""){
                    res.setFactorType('N');
                }else {
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
}
