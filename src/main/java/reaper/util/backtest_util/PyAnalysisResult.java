package reaper.util.backtest_util;

import reaper.bean.ValueDateBean;

import java.util.List;

/**
 * @author keenan on 10/09/2017
 */
public class PyAnalysisResult {
    /**
     * 年化收益率
     */
    private Double nhsyl;

    /**
     * 年化波动率
     */
    private Double nhbdl;

    /**
     * 在险价值
     */
    private Double zxjz;

    /**
     * 收益率序列的下行标准差
     */
    private Double xxbzc;

    /**
     * 夏普比率
     */
    private Double sharpe;

    /**
     * beta
     */
    private Double beta;

    /**
     * 特雷诺指数
     */
    private Double tln;

    /**
     * 择股系数
     */
    private Double zgxs;

    /**
     * 择时系数
     */
    private Double zsxs;

    /**
     * 最大跌幅
     */
    private Double zddf;

    /**
     * 期初净值
     */
    private Double qcjz;

    /**
     * 期末净值
     */
    private Double qmjz;

    /**
     * 平均相关系数
     */
    private List<CorrelationCoefficient> pjxgxs;

    /**
     * 日收益率
     */
    private List<ValueDateBean> dailyReturnRate;

    /**
     * 累计净值
     */
    private List<ValueDateBean> cumNet;

    /**
     * 每日回撤
     */
    private List<ValueDateBean> dailyRetrace;

    public void setPjxgxs(List<CorrelationCoefficient> pjxgxs) {
        this.pjxgxs = pjxgxs;
    }

    public class CorrelationCoefficient {
        private String code1;

        private String code2;

        private double cc;

        public CorrelationCoefficient(String code1, String code2, double cc) {
            this.code1 = code1;
            this.code2 = code2;
            this.cc = cc;
        }

        public String getCode1() {
            return code1;
        }

        public void setCode1(String code1) {
            this.code1 = code1;
        }

        public String getCode2() {
            return code2;
        }

        public void setCode2(String code2) {
            this.code2 = code2;
        }

        public double getCc() {
            return cc;
        }

        public void setCc(double cc) {
            this.cc = cc;
        }

        @Override
        public String toString() {
            return "CorrelationCoefficient{" +
                    "code1='" + code1 + '\'' +
                    ", code2='" + code2 + '\'' +
                    ", cc=" + cc +
                    '}';
        }
    }

    public void setNhsyl(Double nhsyl) {
        this.nhsyl = nhsyl;
    }

    public void setNhbdl(Double nhbdl) {
        this.nhbdl = nhbdl;
    }

    public void setZxjz(Double zxjz) {
        this.zxjz = zxjz;
    }

    public void setXxbzc(Double xxbzc) {
        this.xxbzc = xxbzc;
    }

    public void setSharpe(Double sharpe) {
        this.sharpe = sharpe;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public void setTln(Double tln) {
        this.tln = tln;
    }

    public void setZgxs(Double zgxs) {
        this.zgxs = zgxs;
    }

    public void setZsxs(Double zsxs) {
        this.zsxs = zsxs;
    }

    public void setZddf(Double zddf) {
        this.zddf = zddf;
    }

    public void setQcjz(Double qcjz) {
        this.qcjz = qcjz;
    }

    public void setQmjz(Double qmjz) {
        this.qmjz = qmjz;
    }

    public Double getNhsyl() {
        return nhsyl;
    }

    public Double getNhbdl() {
        return nhbdl;
    }

    public Double getZxjz() {
        return zxjz;
    }

    public Double getXxbzc() {
        return xxbzc;
    }

    public Double getSharpe() {
        return sharpe;
    }

    public Double getBeta() {
        return beta;
    }

    public Double getTln() {
        return tln;
    }

    public Double getZgxs() {
        return zgxs;
    }

    public Double getZsxs() {
        return zsxs;
    }

    public Double getZddf() {
        return zddf;
    }

    public Double getQcjz() {
        return qcjz;
    }

    public Double getQmjz() {
        return qmjz;
    }

    public List<CorrelationCoefficient> getPjxgxs() {
        return pjxgxs;
    }

    public List<ValueDateBean> getDailyReturnRate() {
        return dailyReturnRate;
    }

    public void setDailyReturnRate(List<ValueDateBean> dailyReturnRate) {
        this.dailyReturnRate = dailyReturnRate;
    }

    public List<ValueDateBean> getCumNet() {
        return cumNet;
    }

    public void setCumNet(List<ValueDateBean> cumNet) {
        this.cumNet = cumNet;
    }

    public List<ValueDateBean> getDailyRetrace() {
        return dailyRetrace;
    }

    public void setDailyRetrace(List<ValueDateBean> dailyRetrace) {
        this.dailyRetrace = dailyRetrace;
    }
}
