package org.dog.server.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Odin
 * @Date: 2023/6/12 16:30
 * @Description:
 */

@Getter
@Setter
public class BaseEntity {

    @TableField(exist = false)
    private Map<String, String> params = new HashMap<>();

}
