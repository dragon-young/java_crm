package cn.sixboys.controller;

import cn.sixboys.domain.Department;
import cn.sixboys.domain.JsonResult;
import cn.sixboys.service.IDepartmentService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService iDepartmentService;

    /**
     * 查询判断部门信息是否重复
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public JsonResult getSelect(Department department){

        int select = iDepartmentService.select(department);
        if(select > 0) {
            return new JsonResult(false, "用户名或密码已存在");
        }
        return new JsonResult(true, "插入成功");

    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
@ResponseBody
    public JsonResult delete(Long id){
        iDepartmentService.delete(id);
        return new JsonResult(true,"删除成功");
    }

    /**
     * 插入
     * @param department
     * @return
     */
    @RequestMapping("/input")
    @ResponseBody
    public JsonResult input(Department department){
        iDepartmentService.input(department);
        return new JsonResult(true,"插入成功");

    }

    /**
     * 修改
     * @param department
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(Department department){
        iDepartmentService.update(department);
        return new JsonResult(true,"修改成功");
    }

    /**
     * 分页查询
     * @param pageSize
     * @param currentPage
     * @return
     */
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
        PageResult<Department> query = iDepartmentService.query(queryObject);
        return new JsonResult(true,"查询成功",query);


    }


}
