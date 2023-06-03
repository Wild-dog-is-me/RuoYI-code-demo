package org.dog.server.enums;

/**
 * @Author: Odin
 * @Date: 2023/6/3 14:35
 * @Description: 限流类型
 */

public enum LimitType {

    /**
     * 默认限流策略，针对某个接口限流
     */
    DEFAULT,

    /**
     * 针对某个IP进行限流
     */
    IP


}
