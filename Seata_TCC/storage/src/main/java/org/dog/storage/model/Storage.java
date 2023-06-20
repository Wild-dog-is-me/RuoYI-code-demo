package org.dog.storage.model;

import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/6/18 23:22
 * @Description:
 */

@Data
public class Storage {

    private Integer id;
    private String productId;
    private Integer count;
    private Integer freezeCount;

}
