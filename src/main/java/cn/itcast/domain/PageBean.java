package cn.itcast.domain;

import java.util.List;

public class PageBean<T> {
    private int totalCount;
    private int totalPage;
    private int currentPage;
    private int pageSize;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRoutes() {
        return routes;
    }

    public void setRoutes(List<T> routes) {
        this.routes = routes;
    }

    private List<T> routes;
}
