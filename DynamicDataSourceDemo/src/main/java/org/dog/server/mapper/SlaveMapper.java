package org.dog.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: Odin
 * @Date: 2023/6/18 11:03
 * @Description:
 */

@Mapper
public interface SlaveMapper {

    @Update("update user set age=#{age} where username=#{username}")
    int updateUserAge(@Param("username") String username, @Param("age") Integer age);
}
