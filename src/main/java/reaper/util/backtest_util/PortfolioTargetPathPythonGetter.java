package reaper.util.backtest_util;

import reaper.util.PythonUser;

import java.util.Collections;
import java.util.List;

import static reaper.util.CodeFormatUtil.getCodeList;

/**
 * @author keenan on 01/11/2017
 */
public class PortfolioTargetPathPythonGetter {
    private static final String FILE_TARGET_PATH = "target_path.py";

    /**
     * 根据参数调取python获得基金代码
     *
     * @param lamda       是彩虹条的那个选择
     * @param count       资产间分散为10，因子间分散为3
     * @param sqlkind     1为资产减分散，2为因子间分散
     * @param type_kind   只在资产间分散的时候要用，1债券型，2股票型，3混合型 要分三次调用
     * @param factor_kind 只在因子间分散时要用 要分10次调用
     * @return
     */
    public static List<String> getTargetPathCodes(Integer lamda, Integer count, Integer sqlkind, Integer type_kind, Integer factor_kind) {
        lamda = (lamda == null) ? 5 : lamda;
        count = (count == null) ? 8 : count;
        sqlkind = (sqlkind == null) ? 1 : sqlkind;
        type_kind = (type_kind == null) ? 1 : type_kind;
        factor_kind = (factor_kind == null) ? 1 : factor_kind;

        String pyRes = PythonUser.usePy(FILE_TARGET_PATH, lamda + " " + count + " " + sqlkind + " " + type_kind + " " + factor_kind);

        if (pyRes.equals("") || pyRes == null || pyRes.equals("[]") || pyRes.equals("[ ]")) {
            return Collections.EMPTY_LIST;
        } else {
            return getCodeList(pyRes);
        }
    }
}
