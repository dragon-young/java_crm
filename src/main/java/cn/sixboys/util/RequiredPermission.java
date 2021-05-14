package cn.sixboys.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解权限表达式
 * @author FuqiangWu
 */
@Target(ElementType.METHOD)//贴在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时存在
public @interface RequiredPermission {
    String nane();//权限名称
    String expression();//权限表达式
}
