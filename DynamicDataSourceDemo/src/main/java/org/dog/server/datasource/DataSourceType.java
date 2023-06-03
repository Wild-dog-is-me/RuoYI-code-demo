package org.dog.server.datasource;

/**
 * @Author: Odin
 * @Date: 2023/6/2 12:58
 * @Description:
 */
public interface DataSourceType {

    String DEFAULT_DS_NAME = "master";

    String SLAVE_DS_NAME = "slave";

    String DS_SESSION_KEY = "ds_session_key";

}
