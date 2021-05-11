package cn.sixboys.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author mingjuntang
 * @Data 2021/5/9 14:38
 */

@Getter
@Setter
@ToString
public class Employee {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private Long deptId;
    private Date hireDate;
    private Integer state;
    private Integer admin;
    private Integer roleId;
    //关联属性
    private Department department;
}
