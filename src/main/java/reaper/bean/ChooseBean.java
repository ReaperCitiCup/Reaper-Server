package reaper.bean;


import java.util.List;

/**
 * Created by Feng on 2017/9/10.
 */
public class ChooseBean {
    public String date;
    public List<FieldValueBean> data;

    public ChooseBean(String date, List<FieldValueBean> data) {
        this.date = date;
        this.data = data;
    }
}
