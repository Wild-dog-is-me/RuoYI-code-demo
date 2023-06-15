package org.dog.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Odin
 * @Date: 2023/6/12 15:49
 * @Description:
 */

@Configuration
@MapperScan("org.dog.server.mapper")
public class MybatisPlusConfig {

}

