package cn.sixboys.domain;

import lombok.*;
/**
 * 角色和权限关联表实体类
 * @author FuqiangWu
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role_Permissiion {
    private Long roleId;
    private Long permissionId;
}
