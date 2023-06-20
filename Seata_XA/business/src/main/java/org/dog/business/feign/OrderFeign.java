package org.dog.business.feign;

import org.dog.common.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Odin
 * @Date: 2023/6/16 21:05
 * @Description:
 */
@FeignClient("order")
public interface OrderFeign {
    @PostMapping("/createOrder")
    RespBean createOrder(@RequestParam("account") String account, @RequestParam("productId") String productId, @RequestParam("count") Integer count);

}
