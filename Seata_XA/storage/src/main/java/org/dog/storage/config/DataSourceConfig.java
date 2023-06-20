package org.dog.storage.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author: Odin
 * @Date: 2023/6/21 05:22
 * @Description:
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean("dataSourceProxy")
    @Primary
    public DataSource dataSourceProxy(DruidDataSource druidDataSource) {
        return new DataSourceProxyXA(druidDataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSourceProxy) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceProxy);
        bean.setTransactionFactory(new SpringManagedTransactionFactory());
        return bean.getObject();
    }
}
