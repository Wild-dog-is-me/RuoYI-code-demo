package org.dog.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dog.server.annotation.DataScope;
import org.dog.server.domain.Role;
import org.dog.server.mapper.RoleMapper;
import org.dog.server.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author odin
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2023-06-12 15:14:58
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    @DataScope(deptAlias = "d",userAlias = "u")
    public List<Role> getAllRoles(Role role) {
        return roleMapper.getAllRoles(role);
    }

}




