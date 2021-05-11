package cn.sixboys.service.impl;

import cn.sixboys.service.IPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = "classpath:springmvc.xml")
public class PermissionServiceImplTest {
@Autowired
private IPermissionService permissionService;
    @Test
    public void reload() {
        permissionService.reload();
    }

    @Test
    public void query() {
    }
}