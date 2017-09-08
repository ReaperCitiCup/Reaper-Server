package reaper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brison_result")
public class BrisonResult {
    @Id
    @GeneratedValue
    private Integer id;

    private String code;

    /**
     * 资产配置效益
     */
    private Double allocationEffect;

    /**
     * 债券选择效益
     */
    private Double selectionEffect;

    /**
     * 交叉效益
     */
    private Double interactionEffect;

    /**
     * 总超额效益
     */
    private Double activeReturn;

    /**
     * 国债exposure
     */
    private Double gzExposure;

    /**
     * 地方政府债exposure
     */
    private Double dfzfzExposure;

    /**
     * 金融债exposure
     */
    private Double jrzExposure;

    /**
     * 企业债exposure
     */
    private Double qyzExposure;

    /**
     * 公司债exposure
     */
    private Double gszExposure;

    /**
     * 中期票据exposure
     */
    private Double zqpjExposure;

    /**
     * 短期融资券exposure
     */
    private Double dqrzqExposure;

    /**
     * 定向工具exposure
     */
    private Double dxgjExposure;

    /**
     * 其他exposure
     */
    private Double otherExposure;

    /**
     * 国债return
     */
    private Double gzReturn;

    /**
     * 地方政府债return
     */
    private Double dfzfzReturn;

    /**
     * 金融债return
     */
    private Double jrzReturn;

    /**
     * 企业债return
     */
    private Double qyzReturn;

    /**
     * 公司债return
     */
    private Double gszReturn;

    /**
     * 中期票据return
     */
    private Double zqpjReturn;

    /**
     * 短期融资券return
     */
    private Double dqrzqReturn;

    /**
     * 定向工具return
     */
    private Double dxgjReturn;

    /**
     * 其他return
     */
    private Double otherReturn;

    public BrisonResult() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getAllocationEffect() {
        return allocationEffect;
    }

    public void setAllocationEffect(Double allocationEffect) {
        this.allocationEffect = allocationEffect;
    }

    public Double getSelectionEffect() {
        return selectionEffect;
    }

    public void setSelectionEffect(Double selectionEffect) {
        this.selectionEffect = selectionEffect;
    }

    public Double getInteractionEffect() {
        return interactionEffect;
    }

    public void setInteractionEffect(Double interactionEffect) {
        this.interactionEffect = interactionEffect;
    }

    public Double getActiveReturn() {
        return activeReturn;
    }

    public void setActiveReturn(Double activeReturn) {
        this.activeReturn = activeReturn;
    }

    public Double getGzExposure() {
        return gzExposure;
    }

    public void setGzExposure(Double gzExposure) {
        this.gzExposure = gzExposure;
    }

    public Double getDfzfzExposure() {
        return dfzfzExposure;
    }

    public void setDfzfzExposure(Double dfzfzExposure) {
        this.dfzfzExposure = dfzfzExposure;
    }

    public Double getJrzExposure() {
        return jrzExposure;
    }

    public void setJrzExposure(Double jrzExposure) {
        this.jrzExposure = jrzExposure;
    }

    public Double getQyzExposure() {
        return qyzExposure;
    }

    public void setQyzExposure(Double qyzExposure) {
        this.qyzExposure = qyzExposure;
    }

    public Double getGszExposure() {
        return gszExposure;
    }

    public void setGszExposure(Double gszExposure) {
        this.gszExposure = gszExposure;
    }

    public Double getZqpjExposure() {
        return zqpjExposure;
    }

    public void setZqpjExposure(Double zqpjExposure) {
        this.zqpjExposure = zqpjExposure;
    }

    public Double getDqrzqExposure() {
        return dqrzqExposure;
    }

    public void setDqrzqExposure(Double dqrzqExposure) {
        this.dqrzqExposure = dqrzqExposure;
    }

    public Double getDxgjExposure() {
        return dxgjExposure;
    }

    public void setDxgjExposure(Double dxgjExposure) {
        this.dxgjExposure = dxgjExposure;
    }

    public Double getOtherExposure() {
        return otherExposure;
    }

    public void setOtherExposure(Double otherExposure) {
        this.otherExposure = otherExposure;
    }

    public Double getGzReturn() {
        return gzReturn;
    }

    public void setGzReturn(Double gzReturn) {
        this.gzReturn = gzReturn;
    }

    public Double getDfzfzReturn() {
        return dfzfzReturn;
    }

    public void setDfzfzReturn(Double dfzfzReturn) {
        this.dfzfzReturn = dfzfzReturn;
    }

    public Double getJrzReturn() {
        return jrzReturn;
    }

    public void setJrzReturn(Double jrzReturn) {
        this.jrzReturn = jrzReturn;
    }

    public Double getQyzReturn() {
        return qyzReturn;
    }

    public void setQyzReturn(Double qyzReturn) {
        this.qyzReturn = qyzReturn;
    }

    public Double getGszReturn() {
        return gszReturn;
    }

    public void setGszReturn(Double gszReturn) {
        this.gszReturn = gszReturn;
    }

    public Double getZqpjReturn() {
        return zqpjReturn;
    }

    public void setZqpjReturn(Double zqpjReturn) {
        this.zqpjReturn = zqpjReturn;
    }

    public Double getDqrzqReturn() {
        return dqrzqReturn;
    }

    public void setDqrzqReturn(Double dqrzqReturn) {
        this.dqrzqReturn = dqrzqReturn;
    }

    public Double getDxgjReturn() {
        return dxgjReturn;
    }

    public void setDxgjReturn(Double dxgjReturn) {
        this.dxgjReturn = dxgjReturn;
    }

    public Double getOtherReturn() {
        return otherReturn;
    }

    public void setOtherReturn(Double otherReturn) {
        this.otherReturn = otherReturn;
    }

    @Override
    public String toString() {
        return "BrisonResult{" +
                "code='" + code + '\'' +
                ", allocationEffect=" + allocationEffect +
                ", selectionEffect=" + selectionEffect +
                ", interactionEffect=" + interactionEffect +
                ", activeReturn=" + activeReturn +
                ", gzExposure=" + gzExposure +
                ", dfzfzExposure=" + dfzfzExposure +
                ", jrzExposure=" + jrzExposure +
                ", qyzExposure=" + qyzExposure +
                ", gszExposure=" + gszExposure +
                ", zqpjExposure=" + zqpjExposure +
                ", dqrzqExposure=" + dqrzqExposure +
                ", dxgjExposure=" + dxgjExposure +
                ", otherExposure=" + otherExposure +
                ", gzReturn=" + gzReturn +
                ", dfzfzReturn=" + dfzfzReturn +
                ", jrzReturn=" + jrzReturn +
                ", qyzReturn=" + qyzReturn +
                ", gszReturn=" + gszReturn +
                ", zqpjReturn=" + zqpjReturn +
                ", dqrzqReturn=" + dqrzqReturn +
                ", dxgjReturn=" + dxgjReturn +
                ", otherReturn=" + otherReturn +
                '}';
    }
}
