package reaper.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author keenan on 10/09/2017
 */
public class PyAnalysisResult {
    /**
     * 年化收益率
     */
    public Map<String, Double> nhsyl;

    /**
     * 年化波动率
     */
    public Map<String, Double> nhbdl;

    /**
     * 在险价值
     */
    public Map<String, Double> zxjz;

    /**
     * 收益率序列的下行标准差
     */
    public Map<String, Double> xxbzc;

    /**
     * 夏普比率
     */
    public Map<String, Double> sharpe;

    /**
     * beta
     */
    public Map<String, Double> beta;

    /**
     * 特雷诺指数
     */
    public Map<String, Double> tln;

    /**
     * 择股系数
     */
    public Map<String, Double> zgxs;

    /**
     * 择时系数
     */
    public Map<String, Double> zsxs;

    /**
     * 平均相关系数
     */
    public List<CorrelationCoefficient> pjxgxs;

    public PyAnalysisResult() {
        this.nhsyl = new HashMap<>();
        this.nhbdl = new HashMap<>();
        this.zxjz = new HashMap<>();
        this.xxbzc = new HashMap<>();
        this.sharpe = new HashMap<>();
        this.beta = new HashMap<>();
        this.tln = new HashMap<>();
        this.zgxs = new HashMap<>();
        this.zsxs = new HashMap<>();
        this.pjxgxs = new ArrayList<>();
    }

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

    public void printContent() {
        System.out.println("=*=*=*=*= 年化收益率 =*=*=*=*=");
        for (Map.Entry<String, Double> entry : nhsyl.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("=*=*=*=*= 年化波动率 =*=*=*=*=");
        for (Map.Entry<String, Double> entry : nhbdl.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("=*=*=*=*= 在险价值 =*=*=*=*=");
        for (Map.Entry<String, Double> entry : zxjz.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("=*=*=*=*= 收益率序列的下行标准差 =*=*=*=*=");
        for (Map.Entry<String, Double> entry : xxbzc.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("=*=*=*=*= 夏普比 =*=*=*=*=");
        for (Map.Entry<String, Double> entry : sharpe.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("=*=*=*=*= beta =*=*=*=*=");
        for (Map.Entry<String, Double> entry : beta.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("=*=*=*=*= 特雷诺指数 =*=*=*=*=");
        for (Map.Entry<String, Double> entry : tln.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("=*=*=*=*= 择股系数 =*=*=*=*=");
        for (Map.Entry<String, Double> entry : zgxs.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("=*=*=*=*= 择时系数 =*=*=*=*=");
        for (Map.Entry<String, Double> entry : zsxs.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("=*=*=*=*= 相关系数 =*=*=*=*=");
        for (CorrelationCoefficient correlationCoefficient : pjxgxs) {
            System.out.println(correlationCoefficient.toString());
        }
    }
}
