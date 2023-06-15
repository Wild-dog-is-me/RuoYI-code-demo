package org.dog.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dog.server.domain.User;

import java.util.List;

/**
* @author odin
* @description 针对表【sys_user(用户信息表)】的数据库操作Service
* @createDate 2023-06-12 15:14:58
*/
public interface UserService extends IService<User> {

    List<User> getAllUsers(User user);

}
