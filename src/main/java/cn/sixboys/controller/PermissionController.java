package cn.sixboys.controller;

import cn.sixboys.domain.JsonResult;
import cn.sixboys.domain.Permission;
import cn.sixboys.service.IPermissionService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import cn.sixboys.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
//处理权限加载请求
    @RequiredPermission(nane = "权限加载",expression = "permission:reload")
    @RequestMapping("/reload")
    @ResponseBody
    public JsonResult reload(){

        try {
            permissionService.reload();
            return new JsonResult(true,"权限加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"权限加载失败");
        }

    }

    /**
     * 分页查询
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequiredPermission(nane = "权限管理页面",expression = "permission:query")
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
        PageResult<Permission> query = permissionService.query(queryObject);
        return new JsonResult(true,"查询成功",query);


    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequiredPermission(nane = "权限删除",expression = "permission:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        try {
            permissionService.delete(id);
            return new JsonResult(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
    }

}
