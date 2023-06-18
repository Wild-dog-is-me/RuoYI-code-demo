package org.dog.storage.service;

import org.dog.storage.mapper.StorageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/16 20:55
 * @Description:
 */

@Service
public class StorageService {

    @Resource
    private StorageMapper storageMapper;

    public boolean deduct(String productId, Integer count) {
        int i = storageMapper.deductStorage(productId, count);
        int result = storageMapper.getCountByCommodityCode(productId);
        if (result >= 0) {
            return true;
        }
        throw new RuntimeException("库存不足，扣库存失败");
    }
}
