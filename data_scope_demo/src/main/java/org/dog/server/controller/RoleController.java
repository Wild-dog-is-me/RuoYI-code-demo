package org.dog.server.controller;

import org.dog.server.domain.Role;
import org.dog.server.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/6/13 10:34
 * @Description:
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/getAll")
    public List<Role> getAllRoles(Role role) {
        return roleService.getAllRoles(role);
    }
}
