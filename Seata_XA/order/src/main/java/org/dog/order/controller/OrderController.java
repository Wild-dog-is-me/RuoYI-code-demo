package org.dog.order.controller;

import org.dog.common.RespBean;
import org.dog.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/16 20:46
 * @Description:
 */

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/createOrder")
    public RespBean createOrder(String account, String productId, Integer count) {
        if (orderService.createOrder(account, productId, count)) {
            return RespBean.ok("下单成功");
        }
        return RespBean.error("下单失败");
    }
}
