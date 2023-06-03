package org.dog.server.annotation;

import org.dog.server.enums.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Odin
 * @Date: 2023/6/3 14:36
 * @Description:
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RateLimiter {

    /**
     * 限流的key，主要是指前缀
     */
    String key() default "rate_limit:";

    /**
     * 限流时间窗
     */
    int time() default 60;

    /**
     * 在时间窗内的限流次数
     */
    int count() default 100;

    /**
     * 限流类型
     */
    LimitType limitType() default LimitType.DEFAULT;
}
