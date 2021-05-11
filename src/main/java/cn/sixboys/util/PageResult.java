package cn.sixboys.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页实体类
 * @param <T>
 * @author mingjuntang
 * @Data 2021/5/10 9:35
 */
@Getter
@Setter
public class PageResult<T> {
    //两条用户输入的数据
    private int currentPage;// 当前页码
    private int pageSize;// 每页显示的条数
    //两条sql语句执行的结果
    private int totalCount;// 总条数
    private List<T> data;// 当前页结果集数据
    //三个程序计算的数据
    private int prevPage;// 上一页页码
    private int nextPage;// 下一页页码
    private int totalPage;// 总页数/末页页码

    public PageResult(int currentPage, int pageSize, int totalCount, List<T> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        this.prevPage = currentPage - 1 >= 1 ? currentPage - 1 : 1;
        this.nextPage = currentPage + 1 <= this.totalPage ? currentPage + 1 : this.totalPage;
    }
}
