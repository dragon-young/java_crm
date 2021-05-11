package cn.sixboys.service;

import cn.sixboys.domain.Permission;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;

public interface IPermissionService {

    void reload();

    void delete (Long id);

    /**
     * 完成查询某一页信息的功能
     * @param queryObject
     * @return
     */
    PageResult<Permission> query(QueryObject queryObject);

}
