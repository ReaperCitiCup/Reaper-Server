package reaper.bean;

/**
 * Created by Feng on 2017/9/2.
 */
public class ReturnBean {
    /**
     * 基金代码
     */
    public String id;

    /**
     * 基金名称
     */
    public String name;

    /**
     * 任期收益
     */
    public Double returns;

    public ReturnBean(String id, String name, Double returns) {
        this.id = id;
        this.name = name;
        this.returns = returns;
    }
}
