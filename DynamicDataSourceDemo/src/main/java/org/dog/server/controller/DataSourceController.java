package org.dog.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.dog.server.datasource.DataSourceType;
import org.dog.server.model.User;
import org.dog.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/6/3 10:55
 * @Description:
 */
@Slf4j
@RestController
public class DataSourceController {

    @Autowired
    private UserService userService;

    @PostMapping("/dsType")
    public void setDsType(String dsType, HttpSession session) {
        session.setAttribute(DataSourceType.DS_SESSION_KEY, dsType);
        log.info("数据源切换为:{}", dsType);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
