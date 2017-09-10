package reaper.bean;

import java.util.List;

/**
 * Created by Feng on 2017/9/10.
 */
public class NetworkBean {
    List<NameMiniBean> categories;
    List<NodeDataBean> nodes;
    List<LinkDataBean> links;

    public NetworkBean(List<NameMiniBean> categories, List<NodeDataBean> nodes, List<LinkDataBean> links) {
        this.categories = categories;
        this.nodes = nodes;
        this.links = links;
    }
}
