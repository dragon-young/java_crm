package cn.sixboys.service;

import cn.sixboys.domain.Employee;
import cn.sixboys.service.impl.EmployeeServiceImpl;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/9 15:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class EmployeeServiceImplTest {
    @Autowired
    private IEmployeeService employeeService = new EmployeeServiceImpl();
    @Test
    public void selectEmployee() {
        QueryObject queryObject = new QueryObject();
        PageResult<Employee> employees = employeeService.selectEmployee(queryObject);
        System.out.println(employees);
    }

    @Test
    public void deleteEmployee() {
    }

    @Test
    public void insertEmployee() {
        Employee employee = new Employee();
        employee.setName("李宇航");
        employee.setPassword("123456");
        employee.setEmail("321645@qq.com");
        employee.setAge(21);
        employee.setDeptId(1L);
        employee.setState(2);
        employee.setHireDate(new Date());
        employee.setAdmin(2);
        employee.setRoleId(2);
        employeeService.insertEmployee(employee);
    }

    @Test
    public void updateEmployee() {
        Employee employee = new Employee();
        employee.setId(3L);
        employee.setState(3);
//        employee.setHireDate(new Date());
        employee.setAdmin(3);
        employee.setRoleId(3);
        employeeService.updateEmployee(employee);
    }

    @Test
    public void selectOne() {
        Employee employee = employeeService.selectOne(1L);
        System.out.println(employee);
    }

    @Test
    public void deleteEmployees(){
        int[] ids = new int[]{7,8};
        employeeService.deleteEmployees(ids);
    }

    @Test
    public void addEmployees(){
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setName("王彦斌");
        employee.setPassword("123456");
        employee.setEmail("321645@qq.com");
        employee.setAge(21);
        employee.setDeptId(1L);
        employee.setState(2);
        employee.setHireDate(new Date());
        employee.setAdmin(2);
        employee.setRoleId(2);
        employees.add(employee);
        Employee employee1 = new Employee();
        employee1.setName("李宇航");
        employee1.setPassword("123456");
        employee1.setEmail("321645@qq.com");
        employee1.setAge(21);
        employee1.setDeptId(1L);
        employee1.setState(2);
        employee1.setHireDate(new Date());
        employee1.setAdmin(2);
        employee1.setRoleId(2);
        employees.add(employee1);
        Employee employee2 = new Employee();
        employee2.setName("沈青龙");
        employee2.setPassword("123456");
        employee2.setEmail("321645@qq.com");
        employee2.setAge(21);
        employee2.setDeptId(1L);
        employee2.setState(2);
        employee2.setHireDate(new Date());
        employee2.setAdmin(2);
        employee2.setRoleId(2);
        employees.add(employee2);
        employeeService.addEmployees(employees);
    }

    @Test
    public void selectAll(){
        Employee employee = new Employee();
        List<Employee> employees = employeeService.selectAll(employee);
        System.out.println(employees);
    }
}