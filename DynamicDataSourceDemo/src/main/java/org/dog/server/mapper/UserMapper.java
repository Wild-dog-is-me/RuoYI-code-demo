package org.dog.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dog.server.model.User;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/6/2 10:08
 * @Description:
 */

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUsers();

    @Update("update user set age=100 where id = 1")
    void update();
}
