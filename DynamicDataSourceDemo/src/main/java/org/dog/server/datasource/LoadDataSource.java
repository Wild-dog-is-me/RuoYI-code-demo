package org.dog.server.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import io.seata.rm.datasource.DataSourceProxy;
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
@Component
@EnableConfigurationProperties(DruidProperties.class)
public class LoadDataSource {
    @Autowired
    DruidProperties druidProperties;

    public Map<String, DataSourceProxy> loadAllDataSource() {
        Map<String, DataSourceProxy> map = new HashMap<>();
        Map<String, Map<String, String>> ds = druidProperties.getDs();
        try {
            Set<String> keySet = ds.keySet();
            for (String key : keySet) {
                DataSource dataSource = druidProperties.dataSource((DruidDataSource) DruidDataSourceFactory.createDataSource(ds.get(key)));
                map.put(key, new DataSourceProxy(dataSource));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
