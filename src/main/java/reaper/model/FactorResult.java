package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "factor_result")
public class FactorResult {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 8)
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

    private Double max1;

    private Double max2;

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

    public Double getMax1() {
        return max1;
    }

    public void setMax1(Double max1) {
        this.max1 = max1;
    }

    public Double getMax2() {
        return max2;
    }

    public void setMax2(Double max2) {
        this.max2 = max2;
    }

    /**
     * 除max1，max2以外的属性置为零
     */
    public void initWithZero(){
        beta = 0.0;
        btop = 0.0;
        cm = 0.0;
        cyly = 0.0;
        dljgysy = 0.0;
        dlsb = 0.0;
        dzyqj = 0.0;
        earningsYield = 0.0;
        fdc = 0.0;
        fyhjr = 0.0;
        fzfz = 0.0;
        gfjg = 0.0;
        growth = 0.0;
        gt = 0.0;
        hyyzhj = 0.0;
        jc = 0.0;
        jchg = 0.0;
        jd = 0.0;
        jsj = 0.0;
        jtys = 0.0;
        jx = 0.0;
        jz = 0.0;
        leverage = 0.0;
        liquidity = 0.0;
        momentum = 0.0;
        mt = 0.0;
        nlmy = 0.0;
        nlsize = 0.0;
        qc = 0.0;
        qgzz = 0.0;
        residualvolatility = 0.0;
        size = 0.0;
        smls = 0.0;
        spyl = 0.0;
        sysh = 0.0;
        tx = 0.0;
        yh = 0.0;
        ysjs = 0.0;
        yy = 0.0;
        zh = 0.0;
    }

    /**
     * 将传入的数据相加
     * @param factorResult
     */
    public void addFactorResult(FactorResult factorResult,double portion){
        beta += factorResult.getBeta() * portion;
        btop += factorResult.getBtop() * portion;
        cm += factorResult.getCm() * portion;
        cyly += factorResult.getCyly() * portion;
        dljgysy += factorResult.getDljgysy() * portion;
        dlsb += factorResult.getDlsb() * portion;
        dzyqj += factorResult.getDzyqj() * portion;
        earningsYield += factorResult.getEarningsYield() * portion;
        fdc += factorResult.getFdc() * portion;
        fyhjr += factorResult.getFyhjr() * portion;
        fzfz += factorResult.getFzfz() * portion;
        gfjg += factorResult.getGfjg() * portion;
        growth += factorResult.getGrowth() * portion;
        gt += factorResult.getGt() * portion;
        hyyzhj += factorResult.getHyyzhj() * portion;
        jc += factorResult.getJc() * portion;
        jchg += factorResult.getJchg() * portion;
        jd += factorResult.getJd() * portion;
        jsj += factorResult.getJsj() * portion;
        jtys += factorResult.getJtys() * portion;
        jx += factorResult.getJx() * portion;
        jz += factorResult.getJz() * portion;
        leverage += factorResult.getLeverage() * portion;
        liquidity += factorResult.getLiquidity() * portion;
        momentum += factorResult.getMomentum() * portion;
        mt += factorResult.getMt() * portion;
        nlmy += factorResult.getNlmy() * portion;
        nlsize += factorResult.getNlsize() * portion;
        qc += factorResult.getQc() * portion;
        qgzz += factorResult.getQgzz() * portion;
        residualvolatility += factorResult.getResidualvolatility() * portion;
        size += factorResult.getSize() * portion;
        smls += factorResult.getSmls() * portion;
        spyl += factorResult.getSpyl() * portion;
        sysh += factorResult.getSysh() * portion;
        tx += factorResult.getTx() * portion;
        yh += factorResult.getYh() * portion;
        ysjs += factorResult.getYsjs() * portion;
        yy += factorResult.getYy() * portion;
        zh += factorResult.getZh() * portion;

    }

    /**
     * 除以总份额得到最终结果
     * @param sum 总份额
     */
    public void toResult(double sum){
        beta /= sum;
        btop /= sum;
        cm /= sum;
        cyly /= sum;
        dljgysy /= sum;
        dlsb /= sum;
        dzyqj /= sum;
        earningsYield /= sum;
        fdc /= sum;
        fyhjr /= sum;
        fzfz /= sum;
        gfjg /= sum;
        growth /= sum;
        gt /= sum;
        hyyzhj /= sum;
        jc /= sum;
        jchg /= sum;
        jd /= sum;
        jsj /= sum;
        jtys /= sum;
        jx /= sum;
        jz /= sum;
        leverage /= sum;
        liquidity /= sum;
        momentum /= sum;
        mt /= sum;
        nlmy /= sum;
        nlsize /= sum;
        qc /= sum;
        qgzz /= sum;
        residualvolatility /= sum;
        size /= sum;
        smls /= sum;
        spyl /= sum;
        sysh /= sum;
        tx /= sum;
        yh /= sum;
        ysjs /= sum;
        yy /= sum;
        zh /= sum;
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
