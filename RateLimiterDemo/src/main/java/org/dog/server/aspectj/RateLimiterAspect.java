package org.dog.server.aspectj;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.dog.server.annotation.RateLimiter;
import org.dog.server.enums.LimitType;
import org.dog.server.exception.RateLimitException;
import org.dog.server.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/6/3 15:02
 * @Description:
 */

@Slf4j
@Aspect
@Component
public class RateLimiterAspect {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    RedisScript<Long> redisScript;

    @Before("@annotation(rateLimiter)")
    public void before(JoinPoint point, RateLimiter rateLimiter) throws RateLimitException {
        int time = rateLimiter.time();
        int count = rateLimiter.count();
        String combineKey = getCombineKey(rateLimiter, point);
        try {
            Long number = redisTemplate.execute(redisScript, Collections.singletonList(combineKey), time, count);
            if (number == null || number.intValue() > count) {
                // 超过限流
                log.error("当前接口已达到最大限流次数");
                throw new RateLimitException("访问过于频繁，请稍后访问");
            }
            log.info("一个时间窗内请求次数:{} ｜ 当前请求次数:{} ｜ 缓存的 key 为:{}", count, number, combineKey);
        }catch (Exception e) {
            throw e;
        }

    }

    /**
     * key其实就是接口调用次数缓存在Redis的key
     * 格式：rate_limit:ip-类-方法
     */
    private String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        StringBuilder key = new StringBuilder(rateLimiter.key());
        if (rateLimiter.limitType() == LimitType.IP) {
            key.append(IpUtils.getIpAddr(((ServletRequestAttributes)
                    Objects.requireNonNull(RequestContextHolder.
                            getRequestAttributes())).getRequest())).append("-");
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        key.append(method.getDeclaringClass().getName()) // 类名
                .append("-")
                .append(method.getName()); // 方法名
        return key.toString();
    }

}
