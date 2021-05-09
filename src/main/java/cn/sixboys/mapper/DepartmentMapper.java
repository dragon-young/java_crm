package cn.wfq.ssm.mapper;

import cn.wfq.ssm.domain.Department;
import org.springframework.stereotype.Component;

import java.util.List;

public interface DepartmentMapper {
    /**
     * \
     * @return
     */
    List<Department> listAll();

    void deleteByid(Long id);

    void insert (Department department);

    void update(Department department);
}
