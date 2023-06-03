package org.dog.server.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author: Odin
 * @Date: 2023/6/2 13:17
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidProperties {

    private String type;
    private String driverClassName;
    private Map<String, Map<String, String>> ds;
    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
    private Integer maxWait;

    /**
     * 在外部构造好一个DruidSource对象 这个对象只包含三个属性 url，username，password
     * 在这个方法中给这个对象设置公共属性
     */
    public DataSource dataSource(DruidDataSource dataSource) {
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        return dataSource;
    }


}
