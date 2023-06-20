package org.dog.account.controller;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.dog.account.service.AccountService;
import org.dog.common.feign.AccountServiceApi;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/19 14:53
 * @Description:
 */
@RestController
public class AccountController implements AccountServiceApi {
    @Resource
    AccountService accountService;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String userId, Double money) {
        return accountService.prepare(userId, money);
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        return accountService.commit(actionContext);
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        return accountService.rollback(actionContext);
    }
}
