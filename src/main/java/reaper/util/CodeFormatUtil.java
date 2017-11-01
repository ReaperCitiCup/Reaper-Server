package reaper.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 与基金代码格式化有关的工具类
 *
 * @author keenan on 11/09/2017
 */
public class CodeFormatUtil {
    /**
     * 不足6为则在前面补0
     *
     * @param code 代码
     * @return
     */
    public static String fillCode(String code) {
        while (code.length() < 6) {
            code = "0" + code;
        }
        return code;
    }

    /**
     * 代码间填充空格
     * 格式化py参数
     *
     * @param codes
     * @return
     */
    public static String fillBlank(List<String> codes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < codes.size(); i++) {
            String code = (i == codes.size() - 1) ? codes.get(i) : codes.get(i) + " ";
            builder.append(code);
        }

        return builder.toString();
    }

    /**
     * 代码和权重组合，并填充空格
     * 格式化py参数
     *
     * @param codes
     * @param ratio
     * @return
     */
    public static String combineAndFillBlank(List<String> codes, List<Double> ratio) {
        StringBuilder builder = new StringBuilder();
        if (codes.size() != ratio.size()) {
            return "";
        }

        for (int i = 0; i < codes.size(); i++) {
            String comb = (i == codes.size() - 1) ? codes.get(i) + " " + ratio.get(i) : codes.get(i) + " " + ratio.get(i) + " ";
            builder.append(comb);
        }

        return builder.toString();
    }

    /**
     * 将python输出的基金代码转为 List<String>
     * python输出格式示例为    [4529.0, 2521.0, 710302.0, 710301.0, 519977.0, 519976.0, 4538.0, 531020.0]
     *
     * @param output python输出
     * @return
     */
    public static List<String> getCodeList(String output) {
        String rmv;
        if (output.endsWith("\n")) {
            rmv = output.substring(1, output.length() - 2);
        } else {
            rmv = output.substring(1, output.length() - 1);
        }

        String[] codes = rmv.split(", ");

        List<String> res = new ArrayList<>();
        for (String code : codes) {
            res.add(fillCode(String.valueOf(Double.valueOf(code).intValue())));
        }
        return res;
    }
}
