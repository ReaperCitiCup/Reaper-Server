package reaper.bean;

/**
 * Created by Feng on 2017/9/10.
 */
public class NodeDataBean {
    public String name;
    public String code;
    public Integer category;

    public NodeDataBean(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeDataBean that = (NodeDataBean) o;

        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
