package cn.sixboys.service.impl;



import cn.sixboys.domain.Department;
import cn.sixboys.mapper.DepartmentMapper;
import cn.sixboys.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> listAll() {

        return departmentMapper.listAll();
    }

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByid(id);
    }

    @Override
    public void input(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }
}
