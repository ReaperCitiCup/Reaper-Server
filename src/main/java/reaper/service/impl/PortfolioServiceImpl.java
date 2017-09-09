package reaper.service.impl;

import Asset_Allocation.Asset_Allocation;
import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.springframework.stereotype.Service;
import reaper.service.PortfolioService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author keenan on 08/09/2017
 */
@Service
public class PortfolioServiceImpl implements PortfolioService {
    /**
     * 创建组合时获得各基金的权重
     *
     * @param codes         基金代码
     * @param portfolioType 分散化方法类型
     * @return <基金代码, 权重(未百分化的double)>
     */
    @Override
    public Map<String, Double> calComponentWeight(List<String> codes, int portfolioType) {
        Map<String, Double> resultMap = new HashMap<>();
        Object[] result = null;
        Asset_Allocation assetAllocation = null;
        MWCharArray mwCharArray = null;
        MWNumericArray mwNumericArray = null;
        // small -> large
        Collections.sort(codes);
        String[] strings = codes.toArray(new String[codes.size()]);

        try {
            mwCharArray = new MWCharArray(strings);
            mwNumericArray = new MWNumericArray(portfolioType);
            assetAllocation = new Asset_Allocation();

            result = assetAllocation.asset_arrangement(1, mwCharArray, mwNumericArray);

            String[] res = null;
            if (result != null || result.length != 0) {
                res = result[0].toString().replaceAll("[ ]+", " ").split(" ");
            }

            if (res == null || res.length != codes.size()) {
                return Collections.EMPTY_MAP;
            }

            for (int i = 0; i < codes.size(); i++) {
                resultMap.put(codes.get(i), Double.valueOf(res[i]));
            }
            return resultMap;
        } catch (MWException e) {
            return Collections.EMPTY_MAP;
        } finally {
            MWArray.disposeArray(result);
            MWArray.disposeArray(mwCharArray);
            MWArray.disposeArray(mwNumericArray);
            if (assetAllocation != null) {
                assetAllocation.dispose();
            }
            assetAllocation = null;
        }
    }
}
