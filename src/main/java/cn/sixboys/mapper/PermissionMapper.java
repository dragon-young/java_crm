package cn.sixboys.mapper;

import cn.sixboys.domain.Department;
import cn.sixboys.domain.Permission;
import cn.sixboys.util.QueryObject;

import java.util.List;

public interface PermissionMapper {
    /**
     * 插入权限
     * @param permission
     * @return
     */
    int insert(Permission permission);

    /**
     * 查询所有权限
     * @return
     */
    List<String>selectAllExpreesion();

    /**
     * 删除权限
     * @param id
     */
    void deleteByid(Long id);

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
    List<Permission>queryForList(QueryObject queryObject);


}
