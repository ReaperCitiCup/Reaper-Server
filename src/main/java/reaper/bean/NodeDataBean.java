package reaper.bean;

/**
 * Created by Feng on 2017/9/10.
 */
public class NodeDataBean {
    public String name;

    public NodeDataBean(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeDataBean that = (NodeDataBean) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
