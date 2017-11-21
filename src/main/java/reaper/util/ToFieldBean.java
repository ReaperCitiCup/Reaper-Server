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
        res.add(new FieldValueBean("综合", fixToTwoAndPercent(factorResult.getZh())));
        res.add(new FieldValueBean("机械", fixToTwoAndPercent(factorResult.getJx())));
        res.add(new FieldValueBean("银行", fixToTwoAndPercent(factorResult.getYh())));
        res.add(new FieldValueBean("房地产", fixToTwoAndPercent(factorResult.getFdc())));
        res.add(new FieldValueBean("医药", fixToTwoAndPercent(factorResult.getYy())));
        res.add(new FieldValueBean("餐饮旅游", fixToTwoAndPercent(factorResult.getHyyzhj())));
        res.add(new FieldValueBean("商贸零售", fixToTwoAndPercent(factorResult.getSmls())));
        res.add(new FieldValueBean("建材", fixToTwoAndPercent(factorResult.getJc())));
        res.add(new FieldValueBean("家电", fixToTwoAndPercent(factorResult.getJd())));
        res.add(new FieldValueBean("纺织服装", fixToTwoAndPercent(factorResult.getFzfz())));
        res.add(new FieldValueBean("食品饮料", fixToTwoAndPercent(factorResult.getSpyl())));
        res.add(new FieldValueBean("电子元器件", fixToTwoAndPercent(factorResult.getDzyqj())));
        res.add(new FieldValueBean("交通运输", fixToTwoAndPercent(factorResult.getJtys())));
        res.add(new FieldValueBean("汽车", fixToTwoAndPercent(factorResult.getQc())));
        res.add(new FieldValueBean("轻工制造", fixToTwoAndPercent(factorResult.getQgzz())));
        res.add(new FieldValueBean("电力及公用事业", fixToTwoAndPercent(factorResult.getDljgysy())));
        res.add(new FieldValueBean("通信", fixToTwoAndPercent(factorResult.getTx())));
        res.add(new FieldValueBean("石油石化", fixToTwoAndPercent(factorResult.getSysh())));
        res.add(new FieldValueBean("有色金属", fixToTwoAndPercent(factorResult.getYsjs())));
        res.add(new FieldValueBean("农林牧渔", fixToTwoAndPercent(factorResult.getNlmy())));
        res.add(new FieldValueBean("建筑", fixToTwoAndPercent(factorResult.getJz())));
        res.add(new FieldValueBean("计算机", fixToTwoAndPercent(factorResult.getJsj())));
        res.add(new FieldValueBean("基础化工", fixToTwoAndPercent(factorResult.getJchg())));
        res.add(new FieldValueBean("煤炭", fixToTwoAndPercent(factorResult.getMt())));
        res.add(new FieldValueBean("电力设备", fixToTwoAndPercent(factorResult.getDlsb())));
        res.add(new FieldValueBean("钢铁", fixToTwoAndPercent(factorResult.getGt())));
        res.add(new FieldValueBean("国防军工", fixToTwoAndPercent(factorResult.getGfjg())));
        res.add(new FieldValueBean("非银行金融", fixToTwoAndPercent(factorResult.getFyhjr())));
        res.add(new FieldValueBean("传媒", fixToTwoAndPercent(factorResult.getCm())));
        res.add(new FieldValueBean("行业因子合计", fixToTwoAndPercent(factorResult.getHyyzhj())));
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
        res.add(new FieldValueBean("beta", fixToTwoAndPercent(factorResult.getBeta())));
        res.add(new FieldValueBean("价值", fixToTwoAndPercent(factorResult.getBtop())));
        res.add(new FieldValueBean("盈利能力", fixToTwoAndPercent(factorResult.getEarningsYield())));
        res.add(new FieldValueBean("成长性", fixToTwoAndPercent(factorResult.getGrowth())));
        res.add(new FieldValueBean("杠杆率", fixToTwoAndPercent(factorResult.getLeverage())));
        res.add(new FieldValueBean("流动性", fixToTwoAndPercent(factorResult.getLiquidity())));
        res.add(new FieldValueBean("动量", fixToTwoAndPercent(factorResult.getMomentum())));
        res.add(new FieldValueBean("非线性市值", fixToTwoAndPercent(factorResult.getNlsize())));
        res.add(new FieldValueBean("波动率", fixToTwoAndPercent(factorResult.getResidualvolatility())));
        res.add(new FieldValueBean("市值", fixToTwoAndPercent(factorResult.getSize())));
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
        res.add(new FieldValueBean("资产配置效益", fixToTwoAndPercent(brisonResult.getAllocationEffect())));
        res.add(new FieldValueBean("债券选择效益", fixToTwoAndPercent(brisonResult.getSelectionEffect())));
        res.add(new FieldValueBean("交叉效益", fixToTwoAndPercent(brisonResult.getInteractionEffect())));
        res.add(new FieldValueBean("总超额效益", fixToTwoAndPercent(brisonResult.getActiveReturn())));
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
        List<FieldValueBean> res = new ArrayList<>();
        res.add(new FieldValueBean("资产配置效益", fixToTwoAndPercent(stockBrinsonResult.getAllocationEffect())));
        res.add(new FieldValueBean("股票选择效益", fixToTwoAndPercent(stockBrinsonResult.getSelectionEffect())));
        res.add(new FieldValueBean("交叉效益", fixToTwoAndPercent(stockBrinsonResult.getInteractionEffect())));
        res.add(new FieldValueBean("总超额效益", fixToTwoAndPercent(stockBrinsonResult.getActiveReturn())));
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
        res.add(new FieldValueBean("国债", fixToTwoAndPercent(brisonResult.getGzExposure())));
        res.add(new FieldValueBean("地方政府债", fixToTwoAndPercent(brisonResult.getDfzfzExposure())));
        res.add(new FieldValueBean("金融债", fixToTwoAndPercent(brisonResult.getJrzExposure())));
        res.add(new FieldValueBean("企业债", fixToTwoAndPercent(brisonResult.getQyzExposure())));
        res.add(new FieldValueBean("公司债", fixToTwoAndPercent(brisonResult.getGszExposure())));
        res.add(new FieldValueBean("中期票据", fixToTwoAndPercent(brisonResult.getZqpjExposure())));
        res.add(new FieldValueBean("短期融资券", fixToTwoAndPercent(brisonResult.getDqrzqExposure())));
        res.add(new FieldValueBean("定向工具", fixToTwoAndPercent(brisonResult.getDxgjExposure())));
        res.add(new FieldValueBean("其他", fixToTwoAndPercent(brisonResult.getOtherExposure())));
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
