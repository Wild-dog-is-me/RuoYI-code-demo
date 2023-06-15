package org.dog.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dog.server.domain.UserRole;
import org.dog.server.mapper.UserRoleMapper;
import org.dog.server.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
* @author odin
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
* @createDate 2023-06-12 15:14:58
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {

}




