package org.dog.business.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.dog.business.feign.OrderFeign;
import org.dog.business.feign.StorageFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/16 21:06
 * @Description:
 */

@Service
public class BusinessService {
    @Resource
    StorageFeign storageFeign;
    @Resource
    OrderFeign orderFeign;

    @GlobalTransactional
    public void purchase(String account, String productId, Integer count) {
        orderFeign.createOrder(account, productId, count);
        storageFeign.deduct(productId, count);
    }
}
