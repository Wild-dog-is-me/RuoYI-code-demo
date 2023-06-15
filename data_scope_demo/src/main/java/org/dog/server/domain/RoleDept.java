package org.dog.server.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和部门关联表
 * @TableName sys_role_dept
 */
@TableName(value ="sys_role_dept")
@Data
public class RoleDept extends BaseEntity implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
