package org.dog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dog.server.domain.RoleDept;

/**
* @author odin
* @description 针对表【sys_role_dept(角色和部门关联表)】的数据库操作Mapper
* @createDate 2023-06-12 15:14:58
* @Entity generator.domain.RoleDept
*/

@Mapper
public interface RoleDeptMapper extends BaseMapper<RoleDept> {

}




