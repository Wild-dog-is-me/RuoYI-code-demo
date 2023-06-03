package org.dog.server.controller;

import org.dog.server.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Odin
 * @Date: 2023/6/3 15:26
 * @Description:
 */

@RestController
public class TestController {

    @RateLimiter(time = 10,count = 3)
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
