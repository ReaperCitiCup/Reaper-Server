package reaper.util;

import reaper.bean.FieldValueBean;
import reaper.model.FactorResult;

import java.util.ArrayList;
import java.util.List;

public class ToFieldBean {
    public List<FieldValueBean> factorResultToIndustryAttribution(FactorResult factorResult){
        List<FieldValueBean> res = new ArrayList<>();
        res.add(new FieldValueBean("综合",factorResult.getZh()));
        res.add(new FieldValueBean("机械",factorResult.getJx()));
        res.add(new FieldValueBean("银行",factorResult.getYh()));
        res.add(new FieldValueBean("房地产",factorResult.getFdc()));
        res.add(new FieldValueBean("医药",factorResult.getYy()));
        res.add(new FieldValueBean("餐饮旅游",factorResult.getHyyzhj()));
        res.add(new FieldValueBean("商贸零售",factorResult.getSmls()));
        res.add(new FieldValueBean("建材",factorResult.getJc()));
        res.add(new FieldValueBean("家电",factorResult.getJd()));
        res.add(new FieldValueBean("纺织服装",factorResult.getFzfz()));
        res.add(new FieldValueBean("食品饮料",factorResult.getSpyl()));
        res.add(new FieldValueBean("电子元器件",factorResult.getDzyqj()));
        res.add(new FieldValueBean("交通运输",factorResult.getJtys()));
        res.add(new FieldValueBean("汽车",factorResult.getQc()));
        res.add(new FieldValueBean("轻工制造",factorResult.getQgzz()));
        res.add(new FieldValueBean("电力及公用事业",factorResult.getDljgysy()));
        res.add(new FieldValueBean("通信",factorResult.getTx()));
        res.add(new FieldValueBean("石油石化",factorResult.getSysh()));
        res.add(new FieldValueBean("有色金属",factorResult.getYsjs()));
        res.add(new FieldValueBean("农林牧渔",factorResult.getNlmy()));
        res.add(new FieldValueBean("建筑",factorResult.getJz()));
        res.add(new FieldValueBean("计算机",factorResult.getJsj()));
        res.add(new FieldValueBean("基础化工",factorResult.getJchg()));
        res.add(new FieldValueBean("煤炭",factorResult.getMt()));
        res.add(new FieldValueBean("电力设备",factorResult.getDlsb()));
        res.add(new FieldValueBean("钢铁",factorResult.getGt()));
        res.add(new FieldValueBean("国防军工",factorResult.getGfjg()));
        res.add(new FieldValueBean("非银行金融",factorResult.getFyhjr()));
        res.add(new FieldValueBean("传媒",factorResult.getCm()));
        res.add(new FieldValueBean("行业因子合计",factorResult.getHyyzhj()));
        return res;
    }

    public List<FieldValueBean> factorResultToStyleAttribution(FactorResult factorResult){
        List<FieldValueBean> res = new ArrayList<>();
        res.add(new FieldValueBean("beta",factorResult.getBeta()));
        res.add(new FieldValueBean("价值",factorResult.getBtop()));
        res.add(new FieldValueBean("盈利能力",factorResult.getEarningsYield()));
        res.add(new FieldValueBean("成长性",factorResult.getGrowth()));
        res.add(new FieldValueBean("杠杆率",factorResult.getLeverage()));
        res.add(new FieldValueBean("流动性",factorResult.getLiquidity()));
        res.add(new FieldValueBean("动量",factorResult.getMomentum()));
        res.add(new FieldValueBean("非线性市值",factorResult.getNlsize()));
        res.add(new FieldValueBean("波动率",factorResult.getResidualvolatility()));
        res.add(new FieldValueBean("市值",factorResult.getNlsize()));
        return res;
    }
}
