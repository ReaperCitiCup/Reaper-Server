package reaper.bean;

import java.util.List;

/**
 * Created by Feng on 2017/9/10.
 */
public class PublicOpinionBean {
    /**
     * 日期：第一周、等等
     */
    public String date;

    /**
     * 好中坏的数量
     * 注：
     * 其中，
     * field：好、中、坏
     * value：{Integer/double未定}， 数量
     */
    public List<FieldValueBean> quantity;

    /**
     * 舆情指标
     */
    public Double index;

    public PublicOpinionBean(String date, List<FieldValueBean> quantity, Double index) {
        this.date = date;
        this.quantity = quantity;
        this.index = index;
    }
}
