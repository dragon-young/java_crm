package cn.sixboys.controller;

import cn.sixboys.domain.Department;
import cn.sixboys.domain.JsonResult;
import cn.sixboys.service.IDepartmentService;
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
     * 查询所有
     * @return
     */
    @RequestMapping("/listAll")
    @ResponseBody
    public JsonResult getListAll(){
        List<Department> departments = iDepartmentService.listAll();
        return new JsonResult(true, "查询成功", departments);
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


}
