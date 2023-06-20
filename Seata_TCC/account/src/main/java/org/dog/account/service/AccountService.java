package org.dog.account.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.dog.account.mapper.AccountMapper;
import org.dog.account.model.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author: Odin
 * @Date: 2023/6/18 23:03
 * @Description:
 */

@Slf4j
@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 这里实际上就是准备工作，也是分布式事务一阶段的工作
     * <p>
     * 检查一下账户是否存在，检查一下余额是否充足，把要扣的钱先冻结起来
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean prepare(String userId, Double money) {
        Account account = accountMapper.getAccountByUserId(userId);
        if (account == null) {
            throw new RuntimeException("账户不存在");
        }
        if (account.getMoney() < money) {
            throw new RuntimeException("账户余额不足，预扣款失败");
        }
        //先把钱冻结起来
        account.setFreezeMoney(account.getFreezeMoney() + money);
        account.setMoney(account.getMoney() - money);
        Integer i = accountMapper.updateAccount(account);
        log.info("{} 账户预扣款 {} 元", userId, money);
        return i == 1;
    }
    /**
     * 二阶段 commit 操作
     * <p>
     * 实际扣款阶段
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean commit(BusinessActionContext actionContext) {
        String userId = ((String) actionContext.getActionContext("userId"));
        double money = ((BigDecimal) actionContext.getActionContext("money")).doubleValue();
        Account account = accountMapper.getAccountByUserId(userId);
        if (account.getFreezeMoney() < money) {
            throw new RuntimeException("账户余额不足，扣款失败");
        }
        account.setFreezeMoney(account.getFreezeMoney() - money);
        Integer i = accountMapper.updateAccount(account);
        log.info("{}账户扣款{}元", userId, money);
        return i.equals(1);
    }

    /**
     * 二阶段回滚
     * <p>
     * 把冻结的钱释放
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean rollback(BusinessActionContext actionContext) {
        String userId = ((String) actionContext.getActionContext("userId"));
        double money = ((BigDecimal) actionContext.getActionContext("money")).doubleValue();
        Account account = accountMapper.getAccountByUserId(userId);
        if (account.getFreezeMoney() >= money) {
            account.setMoney(account.getMoney() + money);
            account.setFreezeMoney(account.getFreezeMoney() - money);
            Integer i = accountMapper.updateAccount(account);
            log.info("{}账户释放冻结金额{}元", userId, money);
            return i.equals(1);
        }
        log.info("{}账户冻结金额已释放", userId);
        return true;
    }

}
