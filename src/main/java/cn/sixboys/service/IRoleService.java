package cn.sixboys.service;


import cn.sixboys.domain.Role;
import cn.sixboys.domain.Role_Permissiion;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;

import java.util.List;

public interface IRoleService {

    /**
     * 完成查询某一页信息的功能
     * @param queryObject
     * @return
     */
    PageResult<Role> query(QueryObject queryObject);

    void insert(Role role);
    void input(List<Role_Permissiion> role_permissiion);

    void delete (Long id);
    void deleteids(List<Long> ids);

    void update(Role role);
    void updateIds(Role_Permissiion role_permissiion);

    Long selectGetId(Role role);


}
