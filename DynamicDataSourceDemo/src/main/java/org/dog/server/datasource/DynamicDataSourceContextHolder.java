package org.dog.server.datasource;

/**
 * @Author: Odin
 * @Date: 2023/6/2 13:06
 * @Description:
 */
public class DynamicDataSourceContextHolder {

    private static ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String dsType) {
        CONTEXT_HOLDER.set(dsType);
    }

    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }

}
