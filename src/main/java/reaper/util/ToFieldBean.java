package reaper.util;

import reaper.bean.FieldValueBean;
import reaper.model.BrisonResult;
import reaper.model.FactorResult;

import java.util.ArrayList;
import java.util.List;

public class ToFieldBean {
    public static List<FieldValueBean> factorResultToIndustryAttribution(FactorResult factorResult){
        if(factorResult==null){
            return null;
        }
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

    public static List<FieldValueBean> factorResultToStyleAttribution(FactorResult factorResult){
        if(factorResult==null){
            return null;
        }
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

    public static List<FieldValueBean> brisonResultToFieldValue(BrisonResult brisonResult){
        if(brisonResult==null){
            return null;
        }
        List<FieldValueBean> res = new ArrayList<>();
        res.add(new FieldValueBean("资产配置效益", brisonResult.getAllocationEffect()));
        res.add(new FieldValueBean("债券选择效益", brisonResult.getSelectionEffect()));
        res.add(new FieldValueBean("交叉效益", brisonResult.getInteractionEffect()));
        res.add(new FieldValueBean("总超额效益", brisonResult.getActiveReturn()));
        return res;
    }

    public static List<FieldValueBean> brisonResultToVarietyAttribution(BrisonResult brisonResult){
        if(brisonResult==null){
            return null;
        }
        List<FieldValueBean> res = new ArrayList<>();
        res.add(new FieldValueBean("国债", brisonResult.getGzExposure()));
        res.add(new FieldValueBean("地方政府债", brisonResult.getDfzfzExposure()));
        res.add(new FieldValueBean("金融债", brisonResult.getJrzExposure()));
        res.add(new FieldValueBean("企业债", brisonResult.getQyzExposure()));
        res.add(new FieldValueBean("公司债", brisonResult.getGszExposure()));
        res.add(new FieldValueBean("中期票据", brisonResult.getZqpjExposure()));
        res.add(new FieldValueBean("短期融资券", brisonResult.getDqrzqExposure()));
        res.add(new FieldValueBean("定向工具", brisonResult.getDxgjExposure()));
        res.add(new FieldValueBean("其他", brisonResult.getOtherExposure()));
        return res;
    }
}
