package org.dog.server.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Odin
 * @Date: 2023/6/2 09:52
 * @Description:
 */
@Slf4j
@Component
@EnableConfigurationProperties(DruidProperties.class)
public class LoadDataSource {

    @Autowired
    private DruidProperties druidProperties;

    public Map<String, DataSource> loadAllDataSource() {
        Map<String, DataSource> map = new HashMap<>();
        Map<String, Map<String, String>> ds = druidProperties.getDs();
        try {
            Set<String> keySet = ds.keySet();
            for (String key : keySet) {
                map.put(key, druidProperties.dataSource((DruidDataSource) DruidDataSourceFactory.createDataSource(ds.get(key))));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
