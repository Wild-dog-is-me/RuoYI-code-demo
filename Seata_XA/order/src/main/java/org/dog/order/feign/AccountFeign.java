package org.dog.order.feign;

import org.dog.common.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Odin
 * @Date: 2023/6/16 19:48
 * @Description:
 */

@FeignClient("account")
public interface AccountFeign {

    @PostMapping("/deductAccount")
    RespBean deduct(@RequestParam("account") String account, @RequestParam("money") Double money);
}
