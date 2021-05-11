package cn.sixboys.service;

import cn.sixboys.domain.Employee;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/9 14:44
 */
public interface IEmployeeService {
    PageResult<Employee> selectEmployee(QueryObject queryObject);
    void deleteEmployee(Long id);
    void insertEmployee(Employee employee);
    void updateEmployee(Employee employee);
    Employee selectOne(Long id);
    void deleteEmployees(int[] ids);
    void addEmployees(List<Employee> employees);
    List<Employee> selectAll(Employee employee);
}
