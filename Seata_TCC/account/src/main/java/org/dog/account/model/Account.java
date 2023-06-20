package org.dog.account.model;

import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/6/18 22:56
 * @Description:
 */

@Data
public class Account {

    private Integer id;

    private String userId;

    private Double money;

    private Double freezeMoney;

}
