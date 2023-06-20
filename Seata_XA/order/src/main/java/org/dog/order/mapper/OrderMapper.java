package org.dog.order.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Odin
 * @Date: 2023/6/16 20:28
 * @Description:
 */

@Mapper
public interface OrderMapper {

    @Insert("insert into order_tbl(user_id,commodity_code,count,money) values(#{userId},#{commodity_code},#{count},#{money})")
    int addOrder(@Param("userId") String userId, @Param("commodity_code") String commodity_code, @Param("count") Integer count, @Param("money") Double money);
}
