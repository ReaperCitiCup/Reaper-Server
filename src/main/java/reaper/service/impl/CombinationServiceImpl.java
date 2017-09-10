package reaper.service.impl;

import Asset_Allocation.Asset_Allocation;
import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.springframework.stereotype.Service;
import reaper.bean.AssetTargetPathBean;
import reaper.bean.CategoryFundBean;
import reaper.bean.CombinationMiniBean;
import reaper.bean.FundRatioBean;
import reaper.service.CombinationService;
import reaper.util.ResultMessage;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author keenan on 08/09/2017
 */
@Service
public class CombinationServiceImpl implements CombinationService {
    /**
     * 自定创建组合
     * 用户已登录验证，从登录用户中获得用户 id
     * 组合列表不为空，且配比之和为100
     *
     * @param name  组合名
     * @param funds 组合列表
     * @return
     */
    @Override
    public ResultMessage createCombinationByUser(String name, List<FundRatioBean> funds) {
        return null;
    }

    /**
     * 我的组合列表
     * 用户已登录验证，从登录用户中获得用户 id
     *
     * @return
     */
    @Override
    public List<CombinationMiniBean> findCombinations() {
        return null;
    }

    /**
     * 删除组合
     *
     * @param id 组合id
     * @return
     */
    @Override
    public ResultMessage deleteCombination(String id) {
        return null;
    }

    /**
     * 回测组合
     *
     * @param id        组合id
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    @Override
    public ResultMessage backtestCombination(String id, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    /**
     * 资产配置-选择目标及路径
     *
     * @param targetPath 目标及路径
     * @return
     */
    @Override
    public CategoryFundBean findFundsByTargetAndPath(AssetTargetPathBean targetPath) {
        return null;
    }

    /**
     * 资产配置-选择基金生成组合
     *
     * @param name  组合名
     * @param codes 基金代码
     * @return
     */
    @Override
    public ResultMessage createCombinationByAssetAllocation(String name, List<String> codes) {
        return null;
    }

    /**
     * 创建组合时获得各基金的权重
     *
     * @param codes         基金代码
     * @param portfolioType 分散化方法类型
     * @return <基金代码, 权重(未百分化的double)>
     */
    private Map<String, Double> calComponentWeight(List<String> codes, int portfolioType) {
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
