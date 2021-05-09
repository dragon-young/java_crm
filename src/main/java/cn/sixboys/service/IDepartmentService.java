package cn.sixboys.service;


import cn.sixboys.domain.Department;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IDepartmentService {

    List<Department> listAll();

    void delete (Long id);

    void input(Department department);

    void update(Department department);
}
