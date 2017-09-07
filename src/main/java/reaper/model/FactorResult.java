package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "factor_result")
public class FactorResult {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String code;

    /**
     * 因子类型，N为普通因子，R为风险因子
     */
    private Character factorType;

    private Double beta;

    private Double btop;

    private Double earningsYield;

    private Double growth;

    private Double leverage;

    private Double liquidity;

    private Double momentum;

    private Double nlsize;

    private Double residualvolatility;

    private Double size;

    /**
     * 机械
     */
    private Double jx;

    /**
     * 银行
     */
    private Double yh;

    /**
     * 房地产
     */
    private Double fdc;

    /**
     * 医药
     */
    private Double yy;

    /**
     * 餐饮旅游
     */
    private Double cyly;

    /**
     * 商贸零售
     */
    private Double smls;

    /**
     * 建材
     */
    private Double jc;

    /**
     * 家电
     */
    private Double jd;

    /**
     * 纺织服装
     */
    private Double fzfz;

    /**
     * 食品饮料
     */
    private Double spyl;

    /**
     * 电子元器件
     */
    private Double dzyqj;

    /**
     * 交通运输
     */
    private Double jtys;

    /**
     * 汽车
     */
    private Double qc;

    /**
     * 轻工制造
     */
    private Double qgzz;

    /**
     * 电力及公用事业
     */
    private Double dljgysy;

    /**
     * 综合
     */
    private Double zh;

    /**
     * 通信
     */
    private Double tx;

    /**
     * 石油石化
     */
    private Double sysh;

    /**
     * 有色金属
     */
    private Double ysjs;

    /**
     * 农林牧渔
     */
    private Double nlmy;

    /**
     * 建筑
     */
    private Double jz;

    /**
     * 计算机
     */
    private Double jsj;

    /**
     * 基础化工
     */
    private Double jchg;

    /**
     * 煤炭
     */
    private Double mt;

    /**
     * 电力设备
     */
    private Double dlsb;

    /**
     * 钢铁
     */
    private Double gt;

    /**
     * 国防军工
     */
    private Double gfjg;

    /**
     * 非银行金融
     */
    private Double fyhjr;

    /**
     * 传媒
     */
    private Double cm;

    /**
     * 行业因子合计
     */
    private Double hyyzhj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Character getFactorType() {
        return factorType;
    }

    public void setFactorType(Character factorType) {
        this.factorType = factorType;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public Double getBtop() {
        return btop;
    }

    public void setBtop(Double btop) {
        this.btop = btop;
    }

    public Double getEarningsYield() {
        return earningsYield;
    }

    public void setEarningsYield(Double earningsYield) {
        this.earningsYield = earningsYield;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    public Double getLeverage() {
        return leverage;
    }

    public void setLeverage(Double leverage) {
        this.leverage = leverage;
    }

    public Double getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(Double liquidity) {
        this.liquidity = liquidity;
    }

    public Double getMomentum() {
        return momentum;
    }

    public void setMomentum(Double momentum) {
        this.momentum = momentum;
    }

    public Double getNlsize() {
        return nlsize;
    }

    public void setNlsize(Double nlsize) {
        this.nlsize = nlsize;
    }

    public Double getResidualvolatility() {
        return residualvolatility;
    }

    public void setResidualvolatility(Double residualvolatility) {
        this.residualvolatility = residualvolatility;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getJx() {
        return jx;
    }

    public void setJx(Double jx) {
        this.jx = jx;
    }

    public Double getYh() {
        return yh;
    }

    public void setYh(Double yh) {
        this.yh = yh;
    }

    public Double getFdc() {
        return fdc;
    }

    public void setFdc(Double fdc) {
        this.fdc = fdc;
    }

    public Double getYy() {
        return yy;
    }

    public void setYy(Double yy) {
        this.yy = yy;
    }

    public Double getCyly() {
        return cyly;
    }

    public void setCyly(Double cyly) {
        this.cyly = cyly;
    }

    public Double getSmls() {
        return smls;
    }

    public void setSmls(Double smls) {
        this.smls = smls;
    }

    public Double getJc() {
        return jc;
    }

    public void setJc(Double jc) {
        this.jc = jc;
    }

    public Double getJd() {
        return jd;
    }

    public void setJd(Double jd) {
        this.jd = jd;
    }

    public Double getFzfz() {
        return fzfz;
    }

    public void setFzfz(Double fzfz) {
        this.fzfz = fzfz;
    }

    public Double getSpyl() {
        return spyl;
    }

    public void setSpyl(Double spyl) {
        this.spyl = spyl;
    }

    public Double getDzyqj() {
        return dzyqj;
    }

    public void setDzyqj(Double dzyqj) {
        this.dzyqj = dzyqj;
    }

    public Double getJtys() {
        return jtys;
    }

    public void setJtys(Double jtys) {
        this.jtys = jtys;
    }

    public Double getQc() {
        return qc;
    }

    public void setQc(Double qc) {
        this.qc = qc;
    }

    public Double getQgzz() {
        return qgzz;
    }

    public void setQgzz(Double qgzz) {
        this.qgzz = qgzz;
    }

    public Double getDljgysy() {
        return dljgysy;
    }

    public void setDljgysy(Double dljgysy) {
        this.dljgysy = dljgysy;
    }

    public Double getZh() {
        return zh;
    }

    public void setZh(Double zh) {
        this.zh = zh;
    }

    public Double getTx() {
        return tx;
    }

    public void setTx(Double tx) {
        this.tx = tx;
    }

    public Double getSysh() {
        return sysh;
    }

    public void setSysh(Double sysh) {
        this.sysh = sysh;
    }

    public Double getYsjs() {
        return ysjs;
    }

    public void setYsjs(Double ysjs) {
        this.ysjs = ysjs;
    }

    public Double getNlmy() {
        return nlmy;
    }

    public void setNlmy(Double nlmy) {
        this.nlmy = nlmy;
    }

    public Double getJz() {
        return jz;
    }

    public void setJz(Double jz) {
        this.jz = jz;
    }

    public Double getJsj() {
        return jsj;
    }

    public void setJsj(Double jsj) {
        this.jsj = jsj;
    }

    public Double getJchg() {
        return jchg;
    }

    public void setJchg(Double jchg) {
        this.jchg = jchg;
    }

    public Double getMt() {
        return mt;
    }

    public void setMt(Double mt) {
        this.mt = mt;
    }

    public Double getDlsb() {
        return dlsb;
    }

    public void setDlsb(Double dlsb) {
        this.dlsb = dlsb;
    }

    public Double getGt() {
        return gt;
    }

    public void setGt(Double gt) {
        this.gt = gt;
    }

    public Double getGfjg() {
        return gfjg;
    }

    public void setGfjg(Double gfjg) {
        this.gfjg = gfjg;
    }

    public Double getFyhjr() {
        return fyhjr;
    }

    public void setFyhjr(Double fyhjr) {
        this.fyhjr = fyhjr;
    }

    public Double getCm() {
        return cm;
    }

    public void setCm(Double cm) {
        this.cm = cm;
    }

    public Double getHyyzhj() {
        return hyyzhj;
    }

    public void setHyyzhj(Double hyyzhj) {
        this.hyyzhj = hyyzhj;
    }

    @Override
    public String toString() {
        return "FactorResult{" +
                "code='" + code + '\'' +
                ", factorType=" + factorType +
                ", beta=" + beta +
                ", btop=" + btop +
                ", earningsYield=" + earningsYield +
                ", growth=" + growth +
                ", leverage=" + leverage +
                ", liquidity=" + liquidity +
                ", momentum=" + momentum +
                ", nlsize=" + nlsize +
                ", residualvolatility=" + residualvolatility +
                ", size=" + size +
                ", jx=" + jx +
                ", yh=" + yh +
                ", fdc=" + fdc +
                ", yy=" + yy +
                ", cyly=" + cyly +
                ", smls=" + smls +
                ", jc=" + jc +
                ", jd=" + jd +
                ", fzfz=" + fzfz +
                ", spyl=" + spyl +
                ", dzyqj=" + dzyqj +
                ", jtys=" + jtys +
                ", qc=" + qc +
                ", qgzz=" + qgzz +
                ", dljgysy=" + dljgysy +
                ", zh=" + zh +
                ", tx=" + tx +
                ", sysh=" + sysh +
                ", ysjs=" + ysjs +
                ", nlmy=" + nlmy +
                ", jz=" + jz +
                ", jsj=" + jsj +
                ", jchg=" + jchg +
                ", mt=" + mt +
                ", dlsb=" + dlsb +
                ", gt=" + gt +
                ", gfjg=" + gfjg +
                ", fyhjr=" + fyhjr +
                ", cm=" + cm +
                ", hyyzhj=" + hyyzhj +
                '}';
    }
}
