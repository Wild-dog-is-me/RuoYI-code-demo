package org.dog.account.service;

import org.dog.account.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/16 19:09
 * @Description:
 */

@Service
public class AccountService {
    @Resource
    AccountMapper accountMapper;

    public boolean deductAccount(String account, Double money) {
        accountMapper.updateAccount(account, money);
        Double m = accountMapper.getMoneyByAccount(account);
        if (m >= 0) {
            return true;
        }
        throw new RuntimeException("账户余额不足");
    }

}
