package reaper.util;

import java.text.DecimalFormat;

public class FormatData {
    static DecimalFormat decimalFormat = new DecimalFormat("#.00");

    /**
     * 保留两位小数
     * @param data
     * @return
     */
    public static Double fixToTwo(Double data){
//        return data;
        return data==null?null:Double.valueOf(decimalFormat.format(data));
    }

    /**
     * 百分化并保留两位小数
     * @param data
     * @return
     */
    public static Double fixToTwoAndPercent(Double data){
        return data==null?null:Double.valueOf(decimalFormat.format(data*100));
    }
}
