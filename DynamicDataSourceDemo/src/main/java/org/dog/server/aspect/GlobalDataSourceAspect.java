package org.dog.server.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.dog.server.datasource.DataSourceType;
import org.dog.server.datasource.DynamicDataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @Author: Odin
 * @Date: 2023/6/3 10:59
 * @Description:
 */
// TODO:数据源存入Session中
//@Aspect
//@Component
//@Order(10)
public class GlobalDataSourceAspect {

    @Autowired
    private HttpSession session;

    @Pointcut("execution(* org.dog.server.service.*.*(..))")
    public void pc() {
    }

    @Around("pc()")
    public Object around(ProceedingJoinPoint point) {
        DynamicDataSourceContextHolder.setDataSourceType((String) session.getAttribute(DataSourceType.DS_SESSION_KEY));
        try {
            return point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
        return null;
    }
}
