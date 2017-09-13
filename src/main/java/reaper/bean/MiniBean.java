package reaper.bean;

/**
 * 基金代码+名字
 * Created by Sorumi on 17/8/21.
 */
public class MiniBean {

    /**
     * 代码 id
     */
    public String id;

    /**
     * 名称
     */
    public String name;

    public MiniBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public MiniBean() {
    }
}
