package cn.sixboys.domain;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.*;

import java.util.Date;

/**
 * 客户更进历史实体类
 * @author mingjuntang
 * @Data 2021/5/15 20:23
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TraceRecord {
    private Long id;
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date traceTime;
    private String traceDetails;
    private Long traceTypeId;
    private Integer traceResult;
    private String remarks;
    private Long customerId;
    private Long inputUserId;
    private Integer type;

    private Customer customer;
}
