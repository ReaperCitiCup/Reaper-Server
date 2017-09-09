package reaper.bean;

/**
 * 基金、经理、公司的代码+名字
 * Created by Sorumi on 17/8/21.
 */
public class MiniBean {

    /**
     * 代码 code
     */
    public String code;

    /**
     * 名称
     */
    public String name;

    public MiniBean(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public MiniBean() {
    }
}
