package cn.sixboys.util;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author mingjuntang
 * @Data 2021/5/9 14:43
 */

@Getter
@Setter
@ToString
public class QueryObject {
    private String keyword;
    private Long deptId;
    private Long employeeId;
    private Integer status;
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date endTime;
    private Integer type;
    private int currentPage = 1; // 当前页码，要跳转到哪一页的页码（需要给默认值）
    private int pageSize = 4; // 每页显示条数（需要给默认值）
    public int start = 0;

    public int getStart(){
        return (currentPage - 1) * pageSize;
    }
}
