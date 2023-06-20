package org.dog.business.service;

import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.dog.common.feign.OrderServiceApi;
import org.dog.common.feign.StorageServiceApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/20 21:47
 * @Description:
 */

@Service
public class BusinessService {

    @Resource
    private StorageServiceApi storageServiceApi;

    @Resource
    private OrderServiceApi orderServiceApi;

    @GlobalTransactional
    public void purchase(String account, String productId, Integer count) {
        String xid = RootContext.getXID();
        BusinessActionContext actionContext = new BusinessActionContext();
        actionContext.setXid(xid);
        orderServiceApi.prepare(actionContext, account, productId, count);
        storageServiceApi.prepare(actionContext, productId, count);
    }
}
