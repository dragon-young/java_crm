package cn.sixboys.mapper;

import cn.sixboys.domain.Employee;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/9 14:42
 */
public interface EmployeeMapper {

    /**
     * 通过id,删除员工数据
     * @param id
     */
    void delete(Long id);
    void insert(Employee employee);
    void update(Employee employee);
    Employee selectOne(Long id);

    /**
     * 查询总记录数
     */
    int queryForCount(QueryObject queryObject);

    /**
     * 根据条件查询员工
     */
    List<Employee> queryForList(QueryObject queryObject);

    /**
     * 批量删除
     */
    void deleteEmployees(int[] ids);

    void addEmployees(List<Employee> employees);

    List<Employee> selectAll(Employee employee);
}
