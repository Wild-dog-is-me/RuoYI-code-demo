package org.dog.server;

import org.dog.server.mapper.UserMapper;
import org.dog.server.model.User;
import org.dog.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DynamicDataSourceDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        userService.getAllUsers().forEach(System.out::println);
    }
}
