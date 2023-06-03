package org.dog.server.model;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: Odin
 * @Date: 2023/6/2 13:29
 * @Description:
 */
@Data
@ToString
public class User {

    private Integer id;
    private String username;
    private String age;

}
