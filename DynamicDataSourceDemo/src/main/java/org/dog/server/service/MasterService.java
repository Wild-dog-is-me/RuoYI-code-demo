package org.dog.server.service;

import org.dog.server.annotation.DataSource;
import org.dog.server.mapper.MasterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/18 11:03
 * @Description:
 */

@Service
public class MasterService {

    @Resource
    private MasterMapper masterMapper;

    @DataSource("master")
    public void updateUserAge(String username, Integer age) {
        masterMapper.updateUserAge(username, age);
    }


}
