package org.dog.server.service;

import lombok.extern.slf4j.Slf4j;
import org.dog.server.annotation.DataSource;
import org.dog.server.datasource.DataSourceType;
import org.dog.server.mapper.UserMapper;
import org.dog.server.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/6/2 10:09
 * @Description:
 */

@Slf4j
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

//    @DataSource(DataSourceType.SLAVE_DS_NAME)
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
}
