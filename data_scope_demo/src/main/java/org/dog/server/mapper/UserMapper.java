package org.dog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dog.server.domain.Role;
import org.dog.server.domain.User;

import java.util.List;

/**
* @author odin
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2023-06-12 15:14:58
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> getRolesByUid(Long userId);

    List<User> getAllUsers(User user);
}




