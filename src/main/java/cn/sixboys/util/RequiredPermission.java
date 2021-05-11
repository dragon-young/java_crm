package cn.sixboys.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//贴在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时存在
public @interface RequiredPermission {
    String nane();//权限名称
    String expression();//权限表达式
}
