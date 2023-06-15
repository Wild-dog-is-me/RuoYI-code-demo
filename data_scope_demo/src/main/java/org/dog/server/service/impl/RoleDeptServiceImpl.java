package org.dog.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dog.server.domain.RoleDept;
import org.dog.server.mapper.RoleDeptMapper;
import org.dog.server.service.RoleDeptService;
import org.springframework.stereotype.Service;

/**
* @author odin
* @description 针对表【sys_role_dept(角色和部门关联表)】的数据库操作Service实现
* @createDate 2023-06-12 15:14:58
*/
@Service
public class RoleDeptServiceImpl extends ServiceImpl<RoleDeptMapper, RoleDept>
    implements RoleDeptService {

}




