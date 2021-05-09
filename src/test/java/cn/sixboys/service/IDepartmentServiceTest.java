package cn.sixboys.service;

import cn.sixboys.mapper.DepartmentMapper;
import cn.sixboys.service.impl.DepartmentServiceImpl;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class IDepartmentServiceTest extends TestCase {

    private IDepartmentService departmentService = new DepartmentServiceImpl();

    public void testListAll() {
        departmentService.listAll();

    }

    public void testDelete() {
    }

    public void testInput() {
    }

    public void testUpdate() {
    }
}