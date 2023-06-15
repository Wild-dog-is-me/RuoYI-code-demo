package org.dog.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.expr.Cast;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.dog.server.annotation.DataScope;
import org.dog.server.domain.BaseEntity;
import org.dog.server.domain.Role;
import org.dog.server.domain.User;
import org.dog.server.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Odin
 * @Date: 2023/6/12 17:06
 * @Description:
 */

@Slf4j
@Aspect
@Component
public class DataScopeAspect {

    public static final String DATA_SCOPE_ALL = "1";
    public static final String DATA_SCOPE_CUSTOM = "2";
    public static final String DATA_SCOPE_DEPT = "3";
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";
    public static final String DATA_SCOPE_SELF = "5";
    public static final String DATA_SCOPE = "data_scope";

    @Before("@annotation(dataScope)")
    public void doBefore(JoinPoint joinPoint, DataScope dataScope) {
        clearDataScope(joinPoint);
        // 获取当前登陆用户信息
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getUserId() == 1L) {
            // 说明是超级管理员，不用进行权限过滤
            return;
        }
        StringBuilder sql = new StringBuilder();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            // 获取角色对应数据权限
            String ds = role.getDataScope();
            log.error("ds:{}", ds);
            if (DATA_SCOPE_ALL.equals(ds)) {
                return;
            } else if (DATA_SCOPE_CUSTOM.equals(ds)) {
                sql.append(String.format
                        (" OR %s.dept_id in (select rd.dept_id from sys_role_dept rd where rd.role_id=%d)",
                                dataScope.deptAlias(),
                                role.getRoleId()));
            } else if (DATA_SCOPE_DEPT.equals(ds)) {
                sql.append(String.format
                        (" OR %s.dept_id=%d",
                                dataScope.deptAlias(),
                                user.getDeptId()));
            } else if (DATA_SCOPE_DEPT_AND_CHILD.equals(ds)) {
                sql.append(String.format
                        (" OR %s.dept_id in (SELECT dept_id FROM sys_dept WHERE dept_id=%d or FIND_IN_SET(%d,`ancestors`))",
                                dataScope.deptAlias(),
                                user.getDeptId(),
                                user.getDeptId()));
            } else if (DATA_SCOPE_SELF.equals(ds)) {
                String s = dataScope.deptAlias();
                if ("".equals(s)) {
                    sql.append(" OR 1=0");
                } else {
                    sql.append(String.format(" OR %s.dept_id=%d",
                            dataScope.userAlias(),
                            user.getUserId()));
                }
            }
        }

        Object arg = joinPoint.getArgs()[0];
        if (arg != null && arg instanceof BaseEntity) {

            BaseEntity baseEntity = (BaseEntity) arg;
            baseEntity.getParams().put(DATA_SCOPE, " AND (" + sql.substring(4) + ")");
            log.error("sql:{}", sql);

        }

    }

    /**
     * 如果params中已有参数，则先删除，防止 SQL 注入
     */
    private void clearDataScope(JoinPoint joinPoint) {
        Object arg = joinPoint.getArgs()[0];
        if (arg != null && arg instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) arg;
            baseEntity.getParams().put(DATA_SCOPE, "");
        }
    }


}
