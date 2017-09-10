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
<<<<<<< HEAD
    public Double stockSelect;
=======
    public Integer stockSelect;
>>>>>>> 89f891c2d3a7ff117d57c94c21434c3b546f12b2

    /**
     * 风控能力
     */
    public Integer antirisk;

<<<<<<< HEAD
    public ManagerAbilityBean(ManagerAbility managerAbility) {
        if(managerAbility!=null) {
            experience = managerAbility.getExperience();
            timing = managerAbility.getTiming();
            returns = managerAbility.getReturns();
            stockSelect = managerAbility.getStockSelect();
            antirisk = managerAbility.getAntirisk();
        }
=======
    public ManagerAbilityBean(ManagerRemark managerRemark) {
        experience = managerRemark.getExperience();
        timing = managerRemark.getMarketTimingAbility();
        returns = managerRemark.getYieldAbility();
        stockSelect = managerRemark.getStockOptionAbility();
        antirisk = managerRemark.getWindControlAbility();
>>>>>>> 89f891c2d3a7ff117d57c94c21434c3b546f12b2
    }
}
