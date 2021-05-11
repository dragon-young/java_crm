package cn.sixboys.service;


import cn.sixboys.domain.Department;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
import java.util.List;


=======
>>>>>>> authority
@Component
public interface IDepartmentService {

    int select(Department department);

    void delete (Long id);

    void input(Department department);

    void update(Department department);

    /**
     * 完成查询某一页信息的功能
     * @param queryObject
     * @return
     */
    PageResult<Department> query(QueryObject queryObject);

    List<Department> selectAll(Department department);
}
