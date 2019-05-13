package com.aop;

import com.annotation.DataSourceChoseAnnotation;
import com.multiple.MultipleDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by lenovo on 2019/4/19.
 * Title DataSourceIntercept
 * Package  com.aop
 * Description
 *
 * @Version V1.0
 */
@Aspect
@Component
@Order(1)
public class DataSourceIntercept {

    //定义切点
    @Pointcut("execution(* com.db.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        //注解在方法上的
        DataSourceChoseAnnotation annotation = method.getAnnotation(DataSourceChoseAnnotation.class);
        if(annotation == null){
            //如果方法上未注解 可能注解在类上
            annotation = joinPoint.getTarget().getClass().getAnnotation(DataSourceChoseAnnotation.class);//获取类上面的注解
            if(annotation == null) return;
        }
        String dataSourceType = annotation.dataSource();

        if(null != dataSourceType ){
            MultipleDataSourceHolder.setDataSourceTyep(dataSourceType);
        }
    }
    @After("pointcut()")
    public void after(){
        MultipleDataSourceHolder.clear();
    }
}
