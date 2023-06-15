package org.dog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dog.server.domain.Dept;

import java.util.List;

/**
* @author odin
* @description 针对表【sys_dept(部门表)】的数据库操作Mapper
* @createDate 2023-06-12 15:14:58
* @Entity generator.domain.Dept
*/

@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

    List<Dept> getAllDepts(Dept dept);


}




