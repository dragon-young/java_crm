package cn.wfq.ssm.service.impl;

import cn.wfq.ssm.domain.Department;
import cn.wfq.ssm.mapper.DepartmentMapper;
import cn.wfq.ssm.service.IDepartmentService;
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
