package org.dog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dog.server.domain.Role;

import java.util.List;

/**
* @author odin
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2023-06-12 15:14:58
* @Entity generator.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getAllRoles(Role role);
}




