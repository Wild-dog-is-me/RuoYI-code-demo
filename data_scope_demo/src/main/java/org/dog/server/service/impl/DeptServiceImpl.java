package org.dog.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dog.server.annotation.DataScope;
import org.dog.server.domain.Dept;
import org.dog.server.mapper.DeptMapper;
import org.dog.server.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author odin
* @description 针对表【sys_dept(部门表)】的数据库操作Service实现
* @createDate 2023-06-12 15:14:58
*/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
    implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
//    @DataScope(deptAlias = "d")
    public List<Dept> getAllDepts(Dept dept) {
        return deptMapper.getAllDepts(dept);
    }
}




