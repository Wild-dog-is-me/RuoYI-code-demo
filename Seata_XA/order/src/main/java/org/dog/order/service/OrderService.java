package org.dog.order.service;

import org.dog.common.RespBean;
import org.dog.order.feign.AccountFeign;
import org.dog.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/16 20:27
 * @Description:
 */

@Service
public class OrderService {
    @Resource
    AccountFeign accountFeign;

    @Resource
    OrderMapper orderMapper;

    public boolean createOrder(String account, String productId, Integer count) {
        //先去扣款，假设每个产品100块钱
        RespBean respBean = accountFeign.deduct(account, count * 100.0);
        int i = orderMapper.addOrder(account, productId, count, count * 100.0);
        return i == 1 && respBean.getStatus() == 200;
    }
}
