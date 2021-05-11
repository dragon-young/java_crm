package cn.sixboys.service.impl;

import cn.sixboys.domain.Employee;
import cn.sixboys.mapper.EmployeeMapper;
import cn.sixboys.service.IEmployeeService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/9 14:45
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public PageResult<Employee> selectEmployee(QueryObject queryObject) {
        List<Employee> employees = employeeMapper.queryForList(queryObject);
        // 查询总记录数
        int i = employeeMapper.queryForCount(queryObject);
        PageResult<Employee> pageResult = new PageResult<>(queryObject.getCurrentPage(), queryObject.getPageSize(), i, employees);
        return  pageResult;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeMapper.delete(id);
    }

    @Override
    public void insertEmployee(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.update(employee);
    }

    @Override
    public Employee selectOne(Long id) {
        Employee employee = employeeMapper.selectOne(id);
        return employee;
    }
    @Override
    public void deleteEmployees(int[] ids) {
        employeeMapper.deleteEmployees(ids);
    }

    @Override
    public void addEmployees(List<Employee> employees) {
        employeeMapper.addEmployees(employees);
    }

    @Override
    public List<Employee> selectAll(Employee employee) {
        List<Employee> employees = employeeMapper.selectAll(employee);
        return employees;
    }
}
