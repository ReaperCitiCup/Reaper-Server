package reaper.util;

import reaper.bean.ValueDateBean;

import java.util.Comparator;

/**
 * @author keenan on 01/11/2017
 */
public class ValueDateBeanComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        ValueDateBean bean1 = (ValueDateBean) o1;
        ValueDateBean bean2 = (ValueDateBean) o2;
        return bean1.date.compareTo(bean2.date);
    }
}
