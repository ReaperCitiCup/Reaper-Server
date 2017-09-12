package reaper.bean;

import java.util.List;

/**
 * Created by Feng on 2017/9/10.
 */
public class NetworkBean {
    public List<NodeDataBean> nodes;
    public List<FundLinkDataBean> links;

    public NetworkBean(List<NodeDataBean> nodes, List<FundLinkDataBean> links) {
        this.nodes = nodes;
        this.links = links;
    }


}
