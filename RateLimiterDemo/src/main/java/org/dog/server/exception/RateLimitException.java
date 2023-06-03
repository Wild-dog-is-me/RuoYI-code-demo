package org.dog.server.exception;

/**
 * @Author: Odin
 * @Date: 2023/6/3 14:59
 * @Description:
 */
public class RateLimitException extends Exception{

    public RateLimitException(String message) {
        super(message);
    }
}
