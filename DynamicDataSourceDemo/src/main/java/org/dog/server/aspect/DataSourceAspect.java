package org.dog.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dog.server.annotation.DataSource;
import org.dog.server.datasource.DynamicDataSourceContextHolder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/6/2 12:59
 * @Description:
 */

@Component
@Aspect
@Slf4j
@Order(11)
public class DataSourceAspect {

    /**
     * 类或者方法上存在注解就对其进行拦截
     */
    @Pointcut("@annotation(org.dog.server.annotation.DataSource)" +
            "||@within(org.dog.server.annotation.DataSource)")
    public void pc() {
    }

    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //获取方法上面的注解
        DataSource dataSource = getDataSource(pjp);
        if (dataSource != null) {
            //获取注解中数据源的名称
            String value = dataSource.value();
            DynamicDataSourceContextHolder.setDataSourceType(value);
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    public DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 查找方法的注解
        DataSource annotation = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
        if (annotation != null) {
            // 说明方法上有@DataSource注解
            return annotation;
        }
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
    }
}
