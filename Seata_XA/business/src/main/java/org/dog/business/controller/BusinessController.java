package org.dog.business.controller;

import org.dog.business.service.BusinessService;
import org.dog.common.RespBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/16 21:08
 * @Description:
 */

@RestController
public class BusinessController {
    @Resource
    BusinessService businessService;

    @PostMapping("/order")
    public RespBean order(String account, String productId, Integer count) {
        try {
            businessService.purchase(account, productId, count);
            return RespBean.ok("下单成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("下单失败", e.getMessage());
        }
    }
}
