package org.dog.server.annotation;

import org.dog.server.datasource.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Odin
 * @Date: 2023/6/2 12:57
 * @Description:
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface DataSource {

    String value() default DataSourceType.DEFAULT_DS_NAME;
}
