package org.dog.server.service;

import org.dog.server.annotation.DataSource;
import org.dog.server.mapper.SlaveMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/18 11:03
 * @Description:
 */

@Service
public class SlaveService {

    @Resource
    private SlaveMapper slaveMapper;

    @DataSource("slave")
    public void updateUserAge(String username, Integer age) {
        slaveMapper.updateUserAge(username, age);
        int i = 1 / 0;
    }
}
