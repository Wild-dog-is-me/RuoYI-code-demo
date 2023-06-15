package org.dog.server.controller;

import org.dog.server.domain.Dept;
import org.dog.server.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/6/12 15:17
 * @Description:
 */

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/getAll")
    public List<Dept> getAllDept(Dept dept) {
        return deptService.getAllDepts(dept);
    }
}
