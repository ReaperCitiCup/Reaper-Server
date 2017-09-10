package reaper.bean;

import reaper.model.ManagerRemark;

public class ManagerAbilityBean {
    /**
     * 经验值
     */
    public Integer experience;

    /**
     * 择时能力
     */
    public Integer timing;

    /**
     * 收益能力
     */
    public Integer returns;

    /**
     * 择股能力
     */
    public Integer stockSelect;

    /**
     * 风控能力
     */
    public Integer antirisk;

    public ManagerAbilityBean(ManagerRemark managerRemark) {
        experience = managerRemark.getExperience();
        timing = managerRemark.getMarketTimingAbility();
        returns = managerRemark.getYieldAbility();
        stockSelect = managerRemark.getStockOptionAbility();
        antirisk = managerRemark.getWindControlAbility();
    }
}
