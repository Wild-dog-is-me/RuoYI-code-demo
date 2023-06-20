package org.dog.storage.controller;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.dog.common.feign.StorageServiceApi;
import org.dog.storage.service.StorageService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/20 09:57
 * @Description:
 */

@RestController
public class StorageController implements StorageServiceApi {

    @Resource
    private StorageService storageService;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String productId, Integer count) {
        return storageService.prepare(productId,count);
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        return storageService.commit(actionContext);
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        return storageService.rollback(actionContext);
    }
}
