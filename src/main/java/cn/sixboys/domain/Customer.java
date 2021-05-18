package cn.sixboys.domain;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.*;

import java.util.Date;

/**
 * 客户实体类
 * @author mingjuntang
 * @Data 2021/5/14 21:45
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private Integer age;
    private Integer gender;
    private String tel;
    private String qq;
    private Long jobId;
    private Long sourceId;
    private Long sellerId;
    private Long inputUserId;
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date inputTime;
    private Integer status;
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date positiveTime;

    private Employee employee;
}
