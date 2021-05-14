package cn.sixboys.controller;

import cn.sixboys.domain.*;
import cn.sixboys.service.IRoleService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import cn.sixboys.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理接口
 * @author FuqiangWu
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    /**
     * 角色分页页面
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequiredPermission(nane = "角色页面",expression = "role:query")
    @RequestMapping("/query")
    @ResponseBody
    public JsonResult query(Integer pageSize, Integer currentPage){
        if (pageSize==null){
            pageSize=4;
        }
        if (currentPage==null){
            currentPage=1;
        }
        QueryObject queryObject = new QueryObject();
        queryObject.setPageSize(pageSize);
        queryObject.setCurrentPage(currentPage);
        PageResult<Role> query = roleService.query(queryObject);
        return new JsonResult(true,"查询成功",query);

    }

    /**
     * 插入
     * @param
     * @return
     */
    @RequiredPermission(nane = "角色新增",expression = "role:input")
    @RequestMapping("/input")
    @ResponseBody
    public JsonResult input(String name, String sn, Long[] permissions){
        Role role = new Role();
        role.setName(name);
        role.setSn(sn);
        roleService.insert(role);
        Long getId = roleService.selectGetId(role);
        List<Role_Permissiion> role_permissiionList = new ArrayList<>();
        for(int i = 0; i < permissions.length; i++){
            role_permissiionList.add(new Role_Permissiion(getId, permissions[i]));
        }
        roleService.input(role_permissiionList);
        return new JsonResult(true,"插入成功");

    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequiredPermission(nane = "角色删除",expression = "role:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        // 删除角色
        roleService.delete(id);
        // 根据id删除中间表
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        roleService.deleteids(ids);
        return new JsonResult(true,"删除成功");
    }

    /**
     * 修改信息
     * @param id
     * @param name
     * @param sn
     * @param permissions
     * @return
     */
    @RequiredPermission(nane = "角色修改",expression = "role:update")
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(Long id, String name, String sn, Long[] permissions){
        Role role = new Role();
        role.setName(name);
        role.setSn(sn);
        role.setId(id);
        // 修改角色
        roleService.update(role);
        // 删除中间表的数据
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(id);
        roleService.deleteids(ids);
        // 重新添加新的数据
        List<Role_Permissiion> role_permissiionList = new ArrayList<>();
        for(int i = 0; i < permissions.length; i++){
            role_permissiionList.add(new Role_Permissiion(id, permissions[i]));
        }
        roleService.input(role_permissiionList);
        return new JsonResult(true,"修改成功");

    }
}
