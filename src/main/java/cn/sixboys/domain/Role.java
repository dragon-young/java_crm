package cn.sixboys.domain;

import lombok.*;

import java.util.List;

/**
 * 角色管理实体类
 * @author FuqiangWu
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    private Long id;
    private String sn;
    private String name;
    private List<Permission> permissions;
}
