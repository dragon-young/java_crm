package cn.sixboys.controller.excelListener;

import cn.sixboys.domain.Employee;
import cn.sixboys.mapper.EmployeeMapper;
import cn.sixboys.service.IEmployeeService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/14 18:40
 */
@Component
@Scope("prototype")
public class EmployeeListener extends AnalysisEventListener<Employee> {
    @Resource
    private IEmployeeService employeeService;
    private List<Employee> listData = new ArrayList<>();


    @Override
    public void invoke(Employee date, AnalysisContext analysisContext) {
        System.out.println(date);
        listData.add(date);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(listData);
        employeeService.addEmployees(listData);
    }
}
