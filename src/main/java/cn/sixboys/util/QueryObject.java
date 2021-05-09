package cn.sixboys.util;

public class QueryObject {

    //当前页码，要跳转到哪一页的页码（需要给默认值）
    private int currentPage = 1;
    // 每页显示条数（需要给默认值）
    private int pageSize = 4;

    // 用于 Limit 子句第一个 ? 取值
    public int getStart(){
        return (currentPage - 1) * pageSize;
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


}
