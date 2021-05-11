package cn.sixboys.mapper;
import cn.sixboys.domain.Department;
import cn.sixboys.util.QueryObject;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.logging.Level;

public interface DepartmentMapper {

    int select(Department department);

    void deleteByid(Long id);

    void insert (Department department);

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

    List<Department> selectAll(Department department);
}
