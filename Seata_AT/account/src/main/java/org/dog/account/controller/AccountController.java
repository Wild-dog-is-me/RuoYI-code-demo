package org.dog.account.controller;

import org.dog.account.service.AccountService;
import org.dog.common.RespBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/16 19:12
 * @Description:
 */

@RestController
public class AccountController {
    @Resource
    AccountService accountService;

    @PostMapping("/deductAccount")
    public RespBean deduct(String account, Double money) {
        if (accountService.deductAccount(account, money)) {
            return RespBean.ok("扣款成功");
        }
        return RespBean.error("扣款失败");
    }
}
