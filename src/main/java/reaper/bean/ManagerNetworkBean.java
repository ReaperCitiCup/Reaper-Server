package reaper.bean;

import java.util.List;

public class ManagerNetworkBean {
    public List<NodeDataBean> nodes;
    public List<ManagerLinkDataBean> links;

    public ManagerNetworkBean(List<NodeDataBean> nodes, List<ManagerLinkDataBean> links) {
        this.nodes = nodes;
        this.links = links;
    }
}
