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
     * field：好、坏
     * value：{Integer/double未定}， 数量
     */
    public List<NumBean> quantity;


    public PublicOpinionBean(String date, List<NumBean> quantity) {
        this.date = date;
        this.quantity = quantity;
    }
}
