package reaper.model;

import javax.persistence.*;

/**
 * Created by max on 2017/9/12.
 */

@Entity
@Table(name = "stock_brinson_result")
public class StockBrinsonResult {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String fundId;

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
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

    private Double allocationEffect;

    private Double selectionEffect;

    private Double interactionEffect;

    private Double activeReturn;









}
