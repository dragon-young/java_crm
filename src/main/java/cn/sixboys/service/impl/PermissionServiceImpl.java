package cn.sixboys.service.impl;

import cn.sixboys.domain.Permission;
import cn.sixboys.mapper.PermissionMapper;
import cn.sixboys.service.IPermissionService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import cn.sixboys.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    @Override
    public void reload() {
        List<String> expreesions = permissionMapper.selectAllExpreesion();//查询所有权限表达式
        //直接通过springmvc收集映射方法信息来获取我们每个方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        System.out.println(handlerMethods);
        Collection<HandlerMethod> values = handlerMethods.values();
        for (HandlerMethod method : values){
            RequiredPermission methodAnnotation = method.getMethodAnnotation(RequiredPermission.class);
            if (methodAnnotation==null){
                continue;
            }
            //从注解中获取权限名称和权限表达式
            String expression = methodAnnotation.expression();
            String name = methodAnnotation.nane();
            //判断数据库是否已存在该权限表达式
            if (expreesions.contains(expression)){
                continue;//存在即跳过
            }
            //把数据封装成权限对象
            Permission permission = new Permission();
            permission.setExpression(expression);
            permission.setName(name);
            permissionMapper.insert(permission);//存放进数据库里
            expreesions.add(expression);//此部分正在遍历，放进数据库后在放进集合里，避免遍历混乱；

        }

    }

    @Override
    public void delete(Long id) {
        permissionMapper.deleteByid(id);
    }

    /**
     * 分页查询逻辑
     * @param queryObject
     * @return
     */
    @Override
    public PageResult<Permission> query(QueryObject queryObject) {
        int totalCount = permissionMapper.queryForCount(queryObject);
        if (totalCount==0){
            return new PageResult<>(queryObject.getCurrentPage(), queryObject.getPageSize(),totalCount, Collections.emptyList());
        }
        List<Permission> permissions = permissionMapper.queryForList(queryObject);
        return new PageResult<>(queryObject.getCurrentPage(),queryObject.getPageSize(),totalCount,permissions);

    }
}
