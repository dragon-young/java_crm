package cn.sixboys.mapper;
import cn.sixboys.domain.Department;
import cn.sixboys.util.QueryObject;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.logging.Level;

/**
 * 部门管理mapper
 * @author FuqiangWu
 */
public interface DepartmentMapper {
    /**
     * 查询数量
     * @param department
     * @return
     */
    int select(Department department);

    /**
     * 通过id删除信息
     * @param id
     */
    void deleteByid(Long id);

    /**
     * 插入数据
     * @param department
     */

    void insert (Department department);

    /**
     * 修改信息
     * @param department
     */
    void update(Department department);

    /**
     *查询总条数
     * @param queryObject
     * @return
     */
    int queryForCount(QueryObject queryObject);

    /**
     *查询当前页显示条数信息
     * @param queryObject
     * @return
     */
    List<Department> queryForList(QueryObject queryObject);

    /**
     * 查询所有信息
     * @param department
     * @return
     */
    List<Department> selectAll(Department department);
}
