package org.dog.business.feign;

import org.dog.common.feign.OrderServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Odin
 * @Date: 2023/6/20 21:45
 * @Description:
 */

@FeignClient("order")
public interface OrderServiceApiImpl extends OrderServiceApi {
}
