package reaper.bean;

/**
 * Created by Feng on 2017/9/2.
 */

import java.util.List;

/**
 * 第一个为当前经理，剩下的为其他经理
 */
public class ManagerPerformanceBean {
    /**
     * 经理自己
     */
    List<PerformanceDataBean> managers;

    /**
     * 其他的经理
     */
    List<PerformanceDataBean> others;

    public ManagerPerformanceBean(List<PerformanceDataBean> managers, List<PerformanceDataBean> others) {
        this.managers = managers;
        this.others = others;
    }
}
