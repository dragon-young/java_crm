package cn.sixboys.service.impl;

import cn.sixboys.domain.Department;
import cn.sixboys.domain.JsonResult;
import cn.sixboys.domain.Role;
import cn.sixboys.domain.Role_Permissiion;
import cn.sixboys.mapper.RoleMapper;
import cn.sixboys.service.IRoleService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     *分页查询信息
     * @param queryObject
     * @return
     */
    @Override
    public PageResult<Role> query(QueryObject queryObject) {
        int totalCount = roleMapper.queryForCount(queryObject);
        if (totalCount==0){
            return new PageResult<>(queryObject.getCurrentPage(), queryObject.getPageSize(),totalCount, Collections.emptyList());
        }
        List<Role> roles = roleMapper.queryForList(queryObject);
        return new PageResult<>(queryObject.getCurrentPage(),queryObject.getPageSize(),totalCount,roles);

    }

    @Override
    public void insert(Role role) {
        roleMapper.insert(role);

    }

    @Override
    public void input(List<Role_Permissiion> role_permissiion) {
        roleMapper.input(role_permissiion);

    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteByid(id);
    }

    @Override
    public void deleteids(List<Long> ids) {
        roleMapper.deleteids(ids);

    }

    @Override
    public void update(Role role) {
        roleMapper.update(role);
    }

    @Override
    public void updateIds(Role_Permissiion role_permissiion) {
        roleMapper.updateIds(role_permissiion);

    }

    @Override
    public Long selectGetId(Role role) {
        Long getId = roleMapper.selectGetId(role);
        return getId;
    }

    @Override
    public List<Role> selectAll() {
        List<Role> roles = roleMapper.selectAll();
        return roles;
    }
}
