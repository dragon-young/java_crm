package cn.sixboys.controller;

import cn.sixboys.controller.excelListener.EmployeeListener;
import cn.sixboys.domain.Employee;
import cn.sixboys.domain.JsonResult;
import cn.sixboys.service.IEmployeeService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import cn.sixboys.util.RequiredPermission;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/9 14:47
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private IEmployeeService employeeService;
    @Resource
    private EmployeeListener employeeListener;
    @RequiredPermission(nane = "员工页面",expression = "employee:query")
    @RequestMapping("/query")
    @ResponseBody
    public JsonResult queryEmployee(Integer currentPage, Integer pageSize, String keyword, Long deptId){

        if(currentPage == null) {
            currentPage = 1;
        }
        if(pageSize ==null ) {
            pageSize = 4;
        }

        QueryObject queryObject = new QueryObject();
        queryObject.setCurrentPage(currentPage);
        queryObject.setPageSize(pageSize);
        if(keyword !=null){
            queryObject.setKeyword(keyword);
        }
        if (deptId != null){
            queryObject.setDeptId(deptId);
        }
        PageResult<Employee> employees= employeeService.selectEmployee(queryObject);
        return new JsonResult(true,"查询成功",employees);
    }
    @RequiredPermission(nane = "员工删除",expression = "employee:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        employeeService.deleteEmployee(id);
        return new JsonResult(true,"删除成功");
    }
    @RequiredPermission(nane = "员工插入",expression = "employee:input")
    @RequestMapping("/input")
    @ResponseBody
    public JsonResult input(Employee employee){
        employee.setPassword("123456");
        employee.setHireDate(new Date());
        employeeService.insertEmployee(employee);
        return new JsonResult(true,"插入成功");
    }
    @RequiredPermission(nane = "员工修改",expression = "employee:update")
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(Employee employee){
        employeeService.updateEmployee(employee);
        return new JsonResult(true,"修改成功");
    }
    @RequiredPermission(nane = "员工批量删除",expression = "employee:deleteEmployees")
    @RequestMapping("/deleteEmployees")
    @ResponseBody
    public JsonResult deleteEmployees(int[] ids){
        employeeService.deleteEmployees(ids);
        return new JsonResult(true,"删除成功");
    }

    @RequestMapping("/importEmployees")
    @ResponseBody
    public JsonResult importEmployees(MultipartFile uploadFile)throws Exception{
        InputStream inputStream = uploadFile.getInputStream();
        ExcelReaderBuilder workbook = EasyExcel.read(inputStream, Employee.class, employeeListener);
        ExcelReaderSheetBuilder sheet = workbook.sheet();
        sheet.doRead();
        return new JsonResult(true,"插入成功");
    }
    @RequestMapping("/selectAll")
    @ResponseBody
    public JsonResult selectAll(Employee employee){
        List<Employee> employees = employeeService.selectAll(employee);
        return new JsonResult(true,"查询成功",employees);
    }
}
