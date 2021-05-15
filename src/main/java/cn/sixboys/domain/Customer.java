package cn.sixboys.domain;

import lombok.*;

import java.util.Date;

/**
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
    private Date inputTime;
    private Integer status;
    private Date positiveTime;
}
