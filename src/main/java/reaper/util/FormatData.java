package reaper.util;

import java.text.DecimalFormat;

public class FormatData {
    static DecimalFormat decimalFormat = new DecimalFormat("#.00");
    static DecimalFormat dfFor5 = new DecimalFormat("#.00000");

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
     * 保留两位小数
     * @param data
     * @return
     */
    public static Double fixToFive(Double data){
//        return data;
        return data==null?null:Double.valueOf(dfFor5.format(data));
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
