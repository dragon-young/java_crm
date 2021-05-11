package cn.sixboys.service.impl;

import cn.sixboys.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = "classpath:springmvc.xml")
public class DepartmentServiceImplTest {
   @Autowired
   private IDepartmentService departmentService;

    @Test
    public void select() {
    }

    @Test
    public void delete() {
        departmentService.delete(18l);
    }

    @Test
    public void input() {
    }

    @Test
    public void update() {
    }

    @Test
    public void query() {


    }
}