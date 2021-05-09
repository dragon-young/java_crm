package cn.sixboys.mapper;
import cn.sixboys.domain.Department;
import cn.sixboys.util.QueryObject;


import java.util.List;

public interface DepartmentMapper {

    List<Department> listAll();

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
    List<Department>queryForList(QueryObject queryObject);
}
