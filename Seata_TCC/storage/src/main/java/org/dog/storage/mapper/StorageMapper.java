package org.dog.storage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dog.storage.model.Storage;

/**
 * @Author: Odin
 * @Date: 2023/6/18 23:26
 * @Description:
 */

@Mapper
public interface StorageMapper {

    @Select("select * from storage_tbl where productId=#{productId}")
    Storage getStorageByProductId(String productId);

    @Update("update storage_tbl set count=#{count},freezeCount=#{freezeCount} where productId=#{productId}")
    Integer updateStorage(Storage storage);
}
