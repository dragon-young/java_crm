package cn.wfq.ssm.service;

import cn.wfq.ssm.domain.Department;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IDepartmentService {

    List<Department> listAll();

    void delete (Long id);

    void input(Department department);

    void update(Department department);
}
