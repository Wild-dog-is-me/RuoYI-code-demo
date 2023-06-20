package org.dog.common.exception;

import org.dog.common.RespBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Odin
 * @Date: 2023/6/16 19:24
 * @Description:
 */

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespBean runtimeException(RuntimeException e) {
        return RespBean.error(e.getMessage());
    }
}
