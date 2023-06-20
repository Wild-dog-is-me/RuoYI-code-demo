package org.dog.business.feign;

import org.dog.common.feign.StorageServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Odin
 * @Date: 2023/6/20 21:46
 * @Description:
 */
@FeignClient("storage")
public interface StorageServiceApiImpl extends StorageServiceApi {
}
