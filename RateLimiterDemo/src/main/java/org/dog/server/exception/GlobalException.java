package org.dog.server.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Odin
 * @Date: 2023/6/3 15:00
 * @Description:
 */

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(RateLimitException.class)
    public Map<String, Object> rateLimitException(RateLimitException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("message", e.getMessage());
        return map;
    }

}
