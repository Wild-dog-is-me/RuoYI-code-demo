package org.dog.server.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Odin
 * @Date: 2023/6/2 09:59
 * @Description:
 */

@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(LoadDataSource loadDataSource) {
        // 1.设置所有的数据源
        Map<String, DataSource> allDs = loadDataSource.loadAllDataSource();
        super.setTargetDataSources(new HashMap<>(allDs));
        super.setDefaultTargetDataSource(allDs.get(DataSourceType.DEFAULT_DS_NAME));
        super.afterPropertiesSet();
    }

    /**
     * 这个方法用来返回数据源名称
     * 当系统需要获取数据源的时候
     * 会自动调用该方法获取数据源的名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
