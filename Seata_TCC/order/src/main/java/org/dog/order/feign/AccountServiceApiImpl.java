package org.dog.order.feign;

import org.dog.common.RespBean;
import org.dog.common.feign.AccountServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Odin
 * @Date: 2023/6/20 21:11
 * @Description:
 */

@FeignClient("account")
public interface AccountServiceApiImpl extends AccountServiceApi {
}
