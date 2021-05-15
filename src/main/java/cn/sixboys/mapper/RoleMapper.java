package cn.sixboys.mapper;

import cn.sixboys.domain.Department;
import cn.sixboys.domain.Dictionary;
import cn.sixboys.domain.Role;
import cn.sixboys.domain.Role_Permissiion;
import cn.sixboys.util.QueryObject;

import java.util.List;

/**
 * 角色管理mapper
 * @author FuqiangWu
 */
public interface RoleMapper {
    /**
     *查询总条数
     * @param queryObject
     * @return
     */
    int queryForCount(QueryObject queryObject);

    /**
     *查询当前页显示条数信息
     * @param queryObject
     * @return
     */
    List<Role> queryForList(QueryObject queryObject);

    /**
     * 插入信息
     * @param role
     */
    void insert(Role role);

    /**
     * 插入角色，权限关联表信息
     * @param role_permissiion
     */
    void input(List<Role_Permissiion> role_permissiion);

    /**
     * 删除
     * @param id
     */
    void deleteByid(Long id);

    /**
     * 删除角色，权限关联表信息
     * @param ids
     */
    void deleteids(List<Long> ids);

    /**
     *修改
     * @param role
     */
    void update(Role role);
    void updateIds(Role_Permissiion role_permissiion);

    /**
     * 获取id
     * @param role
     * @return
     */
    Long selectGetId(Role role);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> selectAll();
}
