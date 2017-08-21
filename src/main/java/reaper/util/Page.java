package reaper.util;

import java.util.List;

public class Page<T> {

    /**
     * 当前页数
     */
    private int page;
    /**
     * 每页显示的条目数
     */
    private int size;
    /**
     * 排序字段名称
     */
    private String order;
    /**
     * 总条目数
     */
    private int totalCount;
    /**
     * 当前页数据列表
     */
    private List<T> result;

    public Page() {

    }

    public Page(int page, int size, String order, int totalCount, List<T> result) {
        this.page = page;
        this.size = size;
        this.order = order;
        this.totalCount = totalCount;
        this.result = result;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
