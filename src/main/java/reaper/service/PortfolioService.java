package reaper.service;

import java.util.List;
import java.util.Map;

/**
 * @author keenan on 08/09/2017
 */
public interface PortfolioService {
    /**
     * 创建组合时获得各基金的权重
     *
     * @param codes         基金代码
     * @param portfolioType 分散化方法类型
     * @return <基金代码, 权重(未百分化的double)>
     */
    Map<String, Double> calComponentWeight(List<String> codes, int portfolioType);
}
