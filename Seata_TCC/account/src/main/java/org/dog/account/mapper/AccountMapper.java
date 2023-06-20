package org.dog.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dog.account.model.Account;

/**
 * @Author: Odin
 * @Date: 2023/6/18 22:57
 * @Description:
 */

@Mapper
public interface AccountMapper {

    @Select("select * from account_tbl where userId=#{userId}")
    Account getAccountByUserId(String userId);

    @Update("update account_tbl set money=#{money},freezeMoney=#{freezeMoney} where userId=#{userId}")
    Integer updateAccount(Account account);
}
