package reaper.util;

import reaper.bean.FieldValueBean;
import reaper.model.BrisonResult;
import reaper.model.FactorResult;
import reaper.model.StockBrinsonResult;

import java.util.*;

import static reaper.util.FormatData.*;

public class ToFieldBean {
    /**
     * 行业归因
     *
     * @param factorResult
     * @return
     */
    public static List<FieldValueBean> factorResultToIndustryAttribution(FactorResult factorResult) {
        if (factorResult == null) {
            return null;
        }
        List<FieldValueBean> res = new ArrayList<>();
        //若合计大于100，总的除以100
        double base = factorResult.getHyyzhj()>1?100:1;

        res.add(new FieldValueBean("综合", fixToTwoAndPercent(factorResult.getZh())/base));
        res.add(new FieldValueBean("机械", fixToTwoAndPercent(factorResult.getJx())/base));
        res.add(new FieldValueBean("银行", fixToTwoAndPercent(factorResult.getYh())/base));
        res.add(new FieldValueBean("房地产", fixToTwoAndPercent(factorResult.getFdc())/base));
        res.add(new FieldValueBean("医药", fixToTwoAndPercent(factorResult.getYy())/base));
        res.add(new FieldValueBean("餐饮旅游", fixToTwoAndPercent(factorResult.getHyyzhj())/base));
        res.add(new FieldValueBean("商贸零售", fixToTwoAndPercent(factorResult.getSmls())/base));
        res.add(new FieldValueBean("建材", fixToTwoAndPercent(factorResult.getJc())/base));
        res.add(new FieldValueBean("家电", fixToTwoAndPercent(factorResult.getJd())/base));
        res.add(new FieldValueBean("纺织服装", fixToTwoAndPercent(factorResult.getFzfz())/base));
        res.add(new FieldValueBean("食品饮料", fixToTwoAndPercent(factorResult.getSpyl())/base));
        res.add(new FieldValueBean("电子元器件", fixToTwoAndPercent(factorResult.getDzyqj())/base));
        res.add(new FieldValueBean("交通运输", fixToTwoAndPercent(factorResult.getJtys())/base));
        res.add(new FieldValueBean("汽车", fixToTwoAndPercent(factorResult.getQc())/base));
        res.add(new FieldValueBean("轻工制造", fixToTwoAndPercent(factorResult.getQgzz())/base));
        res.add(new FieldValueBean("电力及公用事业", fixToTwoAndPercent(factorResult.getDljgysy())/base));
        res.add(new FieldValueBean("通信", fixToTwoAndPercent(factorResult.getTx())/base));
        res.add(new FieldValueBean("石油石化", fixToTwoAndPercent(factorResult.getSysh())/base));
        res.add(new FieldValueBean("有色金属", fixToTwoAndPercent(factorResult.getYsjs())/base));
        res.add(new FieldValueBean("农林牧渔", fixToTwoAndPercent(factorResult.getNlmy())/base));
        res.add(new FieldValueBean("建筑", fixToTwoAndPercent(factorResult.getJz())/base));
        res.add(new FieldValueBean("计算机", fixToTwoAndPercent(factorResult.getJsj())/base));
        res.add(new FieldValueBean("基础化工", fixToTwoAndPercent(factorResult.getJchg())/base));
        res.add(new FieldValueBean("煤炭", fixToTwoAndPercent(factorResult.getMt())/base));
        res.add(new FieldValueBean("电力设备", fixToTwoAndPercent(factorResult.getDlsb())/base));
        res.add(new FieldValueBean("钢铁", fixToTwoAndPercent(factorResult.getGt())/base));
        res.add(new FieldValueBean("国防军工", fixToTwoAndPercent(factorResult.getGfjg())/base));
        res.add(new FieldValueBean("非银行金融", fixToTwoAndPercent(factorResult.getFyhjr())/base));
        res.add(new FieldValueBean("传媒", fixToTwoAndPercent(factorResult.getCm())/base));
        res.add(new FieldValueBean("行业因子合计", fixToTwoAndPercent(factorResult.getHyyzhj())/base));
        return res;
    }

    public static Map<String, Double> map_factorResultToIndustryAttribution(FactorResult factorResult) {
        if (factorResult == null) {
            return Collections.EMPTY_MAP;
        }
        Map<String, Double> res = new HashMap<>();
        res.put("综合", factorResult.getZh());
        res.put("机械", factorResult.getJx());
        res.put("银行", factorResult.getYh());
        res.put("房地产", factorResult.getFdc());
        res.put("医药", factorResult.getYy());
        res.put("餐饮旅游", factorResult.getCyly());
        res.put("商贸零售", factorResult.getSmls());
        res.put("建材", factorResult.getJc());
        res.put("家电", factorResult.getJd());
        res.put("纺织服装", factorResult.getFzfz());
        res.put("食品饮料", factorResult.getSpyl());
        res.put("电子元器件", factorResult.getDzyqj());
        res.put("交通运输", factorResult.getJtys());
        res.put("汽车", factorResult.getQc());
        res.put("轻工制造", factorResult.getQgzz());
        res.put("电力及公用事业", factorResult.getDljgysy());
        res.put("通信", factorResult.getTx());
        res.put("石油石化", factorResult.getSysh());
        res.put("有色金属", factorResult.getYsjs());
        res.put("农林牧渔", factorResult.getNlmy());
        res.put("建筑", factorResult.getJz());
        res.put("计算机", factorResult.getJsj());
        res.put("基础化工", factorResult.getJchg());
        res.put("煤炭", factorResult.getMt());
        res.put("电力设备", factorResult.getDlsb());
        res.put("钢铁", factorResult.getGt());
        res.put("国防军工", factorResult.getGfjg());
        res.put("非银行金融", factorResult.getFyhjr());
        res.put("传媒", factorResult.getCm());
        res.put("行业因子合计", factorResult.getHyyzhj());
        return res;
    }

    /**
     * 风格归因
     *
     * @param factorResult
     * @return
     */
    public static List<FieldValueBean> factorResultToStyleAttribution(FactorResult factorResult) {
        if (factorResult == null) {
            return null;
        }
        List<FieldValueBean> res = new ArrayList<>();
        //若大于100，总的除以100
        double base = 1;
        if(factorResult.getBeta()>1){
            base = 100;
        }else if(factorResult.getBtop()>1){
            base = 100;
        }else if(factorResult.getEarningsYield()>1){
            base = 100;
        }else if(factorResult.getGrowth()>1){
            base = 100;
        }else if(factorResult.getLeverage()>1){
            base = 100;
        }else if(factorResult.getLiquidity()>1){
            base = 100;
        }else if(factorResult.getMomentum()>1){
            base = 100;
        }else if(factorResult.getNlsize()>1){
            base = 100;
        }else if(factorResult.getResidualvolatility()>1){
            base = 100;
        }else if(factorResult.getSize()>1){
            base = 100;
        }

        res.add(new FieldValueBean("beta", fixToTwoAndPercent(factorResult.getBeta())/base));
        res.add(new FieldValueBean("价值", fixToTwoAndPercent(factorResult.getBtop())/base));
        res.add(new FieldValueBean("盈利能力", fixToTwoAndPercent(factorResult.getEarningsYield())/base));
        res.add(new FieldValueBean("成长性", fixToTwoAndPercent(factorResult.getGrowth())/base));
        res.add(new FieldValueBean("杠杆率", fixToTwoAndPercent(factorResult.getLeverage())/base));
        res.add(new FieldValueBean("流动性", fixToTwoAndPercent(factorResult.getLiquidity())/base));
        res.add(new FieldValueBean("动量", fixToTwoAndPercent(factorResult.getMomentum())/base));
        res.add(new FieldValueBean("非线性市值", fixToTwoAndPercent(factorResult.getNlsize())/base));
        res.add(new FieldValueBean("波动率", fixToTwoAndPercent(factorResult.getResidualvolatility())/base));
        res.add(new FieldValueBean("市值", fixToTwoAndPercent(factorResult.getSize())/base));
        return res;
    }

    public static Map<String, Double> map_factorResultToStyleAttribution(FactorResult factorResult) {
        if (factorResult == null) {
            return Collections.EMPTY_MAP;
        }
        Map<String, Double> res = new HashMap<>();
        res.put("beta", factorResult.getBeta());
        res.put("价值", factorResult.getBtop());
        res.put("盈利能力", factorResult.getEarningsYield());
        res.put("成长性", factorResult.getGrowth());
        res.put("杠杆率", factorResult.getLeverage());
        res.put("流动性", factorResult.getLiquidity());
        res.put("动量", factorResult.getMomentum());
        res.put("非线性市值", factorResult.getNlsize());
        res.put("波动率", factorResult.getResidualvolatility());
        res.put("市值", factorResult.getSize());
        return res;
    }

    /**
     * Brison归因-债券
     *
     * @param brisonResult
     * @return
     */
    public static List<FieldValueBean> brisonResultToFieldValue(BrisonResult brisonResult) {
        if (brisonResult == null) {
            return null;
        }
        List<FieldValueBean> res = new ArrayList<>();
        double base = 1;
        if(brisonResult.getAllocationEffect()>1){
            base = 100;
        }else if(brisonResult.getSelectionEffect()>1){
            base = 100;
        }else if(brisonResult.getInteractionEffect()>1){
            base = 100;
        }else if(brisonResult.getActiveReturn()>1){
            base = 100;
        }

        res.add(new FieldValueBean("资产配置效益", fixToTwoAndPercent(brisonResult.getAllocationEffect())/base));
        res.add(new FieldValueBean("债券选择效益", fixToTwoAndPercent(brisonResult.getSelectionEffect())/base));
        res.add(new FieldValueBean("交叉效益", fixToTwoAndPercent(brisonResult.getInteractionEffect())/base));
        res.add(new FieldValueBean("总超额效益", fixToTwoAndPercent(brisonResult.getActiveReturn())/base));
        return res;
    }

    public static Map<String, Double> map_brisonResultToFieldValue(BrisonResult brisonResult) {
        if (brisonResult == null) {
            return null;
        }
        Map<String, Double> res = new HashMap<>();
        res.put("资产配置效益", FormatData.fixToTwoAndPercent(brisonResult.getAllocationEffect()));
        res.put("债券选择效益", FormatData.fixToTwoAndPercent(brisonResult.getSelectionEffect()));
        res.put("交叉效益", FormatData.fixToTwoAndPercent(brisonResult.getInteractionEffect()));
        res.put("总超额效益", FormatData.fixToTwoAndPercent(brisonResult.getActiveReturn()));
        return res;
    }

    /**
     * Brison归因-股票
     *
     * @param stockBrinsonResult
     * @return
     */
    public static List<FieldValueBean> stockBrisonResultToFieldValue(StockBrinsonResult stockBrinsonResult) {
        if (stockBrinsonResult == null) {
            return null;
        }

        double base = 1;
        if(stockBrinsonResult.getAllocationEffect()>1){
            base = 100;
        }else if(stockBrinsonResult.getSelectionEffect()>1){
            base = 100;
        }else if(stockBrinsonResult.getInteractionEffect()>1){
            base = 100;
        }else if(stockBrinsonResult.getActiveReturn()>1){
            base = 100;
        }

        List<FieldValueBean> res = new ArrayList<>();
        res.add(new FieldValueBean("资产配置效益", fixToTwoAndPercent(stockBrinsonResult.getAllocationEffect())/base));
        res.add(new FieldValueBean("股票选择效益", fixToTwoAndPercent(stockBrinsonResult.getSelectionEffect())/base));
        res.add(new FieldValueBean("交叉效益", fixToTwoAndPercent(stockBrinsonResult.getInteractionEffect())/base));
        res.add(new FieldValueBean("总超额效益", fixToTwoAndPercent(stockBrinsonResult.getActiveReturn())/base));
        return res;
    }

    public static Map<String, Double> map_stockBrisonResultToFieldValue(StockBrinsonResult brisonResult) {
        if (brisonResult == null) {
            return Collections.EMPTY_MAP;
        }
        Map<String, Double> res = new HashMap<>();
        res.put("资产配置效益", fixToTwoAndPercent(brisonResult.getAllocationEffect()));
        res.put("股票选择效益", fixToTwoAndPercent(brisonResult.getSelectionEffect()));
        res.put("交叉效益", fixToTwoAndPercent(brisonResult.getInteractionEffect()));
        res.put("总超额效益", fixToTwoAndPercent(brisonResult.getActiveReturn()));
        return res;
    }

    /**
     * 品种归因
     *
     * @param brisonResult
     * @return
     */
    public static List<FieldValueBean> brisonResultToVarietyAttribution(BrisonResult brisonResult) {
        if (brisonResult == null) {
            return null;
        }
        List<FieldValueBean> res = new ArrayList<>();
        double base = 1;
        if(brisonResult.getGzExposure()>1){
            base = 100;
        }else if(brisonResult.getDfzfzExposure()>1){
            base = 100;
        }else if(brisonResult.getJrzExposure()>1){
            base = 100;
        }else if(brisonResult.getQyzExposure()>1){
            base = 100;
        }else if(brisonResult.getGszExposure()>1){
            base = 100;
        }else if(brisonResult.getZqpjExposure()>1){
            base = 100;
        }else if(brisonResult.getDqrzqExposure()>1){
            base = 100;
        }else if(brisonResult.getDxgjExposure()>1){
            base = 100;
        }

        res.add(new FieldValueBean("国债", fixToTwoAndPercent(brisonResult.getGzExposure())/base));
        res.add(new FieldValueBean("地方政府债", fixToTwoAndPercent(brisonResult.getDfzfzExposure())/base));
        res.add(new FieldValueBean("金融债", fixToTwoAndPercent(brisonResult.getJrzExposure())/base));
        res.add(new FieldValueBean("企业债", fixToTwoAndPercent(brisonResult.getQyzExposure())/base));
        res.add(new FieldValueBean("公司债", fixToTwoAndPercent(brisonResult.getGszExposure())/base));
        res.add(new FieldValueBean("中期票据", fixToTwoAndPercent(brisonResult.getZqpjExposure())/base));
        res.add(new FieldValueBean("短期融资券", fixToTwoAndPercent(brisonResult.getDqrzqExposure())/base));
        res.add(new FieldValueBean("定向工具", fixToTwoAndPercent(brisonResult.getDxgjExposure())/base));
        res.add(new FieldValueBean("其他", fixToTwoAndPercent(brisonResult.getOtherExposure())/base));
        return res;
    }

    public static Map<String, Double> map_brisonResultToVarietyAttribution(BrisonResult brisonResult) {
        if (brisonResult == null) {
            return Collections.EMPTY_MAP;
        }
        Map<String, Double> res = new HashMap<>();
        res.put("国债", brisonResult.getGzExposure());
        res.put("地方政府债", brisonResult.getDfzfzExposure());
        res.put("金融债", brisonResult.getJrzExposure());
        res.put("企业债", brisonResult.getQyzExposure());
        res.put("公司债", brisonResult.getGszExposure());
        res.put("中期票据", brisonResult.getZqpjExposure());
        res.put("短期融资券", brisonResult.getDqrzqExposure());
        res.put("定向工具", brisonResult.getDxgjExposure());
        res.put("其他", brisonResult.getOtherExposure());
        return res;
    }
}
