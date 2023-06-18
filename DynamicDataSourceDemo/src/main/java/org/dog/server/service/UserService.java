package org.dog.server.service;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.dog.server.annotation.DataSource;
import org.dog.server.datasource.DataSourceType;
import org.dog.server.mapper.UserMapper;
import org.dog.server.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private MasterService masterService;

    @Resource
    private SlaveService slaveService;

    @GlobalTransactional
    public void test() {
        masterService.updateUserAge("小丽", 99);
        slaveService.updateUserAge("小狗", 99);
    }

//    @DataSource(DataSourceType.SLAVE_DS_NAME)
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
}
