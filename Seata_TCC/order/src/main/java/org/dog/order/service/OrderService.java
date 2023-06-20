package org.dog.order.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.feign.AccountServiceApi;
import org.dog.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/20 09:59
 * @Description:
 */

@Slf4j
@Service
public class OrderService {

    @Resource
    AccountServiceApi accountServiceApi;

    @Resource
    OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean prepare(BusinessActionContext actionContext, String userId, Integer count, String productId) {
        //先去扣款，假设每个产品 100 块钱
        boolean prepare = accountServiceApi.prepare(actionContext, userId, count * 100.0);
        log.info("{} 用户购买了 {} 商品，共计 {} 件，预下单成功", userId, productId, count);
        return prepare;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean commit(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        Integer i = orderMapper.addOrder(userId, productId, count,count*100.0);
        log.info("{} 用户购买了 {} 商品，共计 {} 件，下单成功", userId, productId, count);
        return i == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean rollback(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        log.info("{} 用户购买了 {} 商品，共计 {} 件，订单回滚成功", userId, productId, count);
        return true;
    }
}
