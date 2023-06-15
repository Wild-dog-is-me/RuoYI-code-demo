package org.dog.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dog.server.domain.Dept;

import java.util.List;

/**
* @author odin
* @description 针对表【sys_dept(部门表)】的数据库操作Service
* @createDate 2023-06-12 15:14:58
*/
public interface DeptService extends IService<Dept> {

    List<Dept> getAllDepts(Dept dept);
}
