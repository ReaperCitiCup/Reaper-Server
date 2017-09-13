package reaper.bean;

public class ManagerLinkDataBean {
    public Integer source;
    public Integer target;

    /**
     * 共事天数
     */
    public Integer days;

    /**
     * 共事次数
     */
    public Integer times;

    public ManagerLinkDataBean(Integer source, Integer target, Integer days, Integer times) {
        this.source = source;
        this.target = target;
        this.days = days;
        this.times = times;
    }
}
