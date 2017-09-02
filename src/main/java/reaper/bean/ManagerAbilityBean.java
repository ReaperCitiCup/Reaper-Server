package reaper.bean;

import reaper.model.ManagerAbility;

public class ManagerAbilityBean {
    /**
     * 经验值
     */
    public Double experience;

    /**
     * 择时能力
     */
    public Double timing;

    /**
     * 收益率
     */
    public Double returns;

    /**
     * 稳定性
     */
    public Double stability;

    /**
     * 抗风险
     */
    public Double antirisk;

    public ManagerAbilityBean(ManagerAbility managerAbility) {
        if(managerAbility!=null) {
            experience = managerAbility.getExperience();
            timing = managerAbility.getTiming();
            returns = managerAbility.getReturns();
            stability = managerAbility.getStability();
            antirisk = managerAbility.getAntirisk();
        }
    }
}
