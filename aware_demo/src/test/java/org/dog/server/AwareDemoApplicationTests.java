package org.dog.server;

import org.dog.server.service.BeanUtils;
import org.dog.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AwareDemoApplicationTests {

    @Test
    void contextLoads() {
        UserService userService = BeanUtils.getBean("userService");
        userService.sayHello();
    }

}
