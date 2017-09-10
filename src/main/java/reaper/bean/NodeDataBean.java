package reaper.bean;

/**
 * Created by Feng on 2017/9/10.
 */
public class NodeDataBean {
    public String name;
    public Integer value;
    public Integer category;

    public NodeDataBean(String name, Integer value, Integer category) {
        this.name = name;
        this.value = value;
        this.category = category;
    }
}
