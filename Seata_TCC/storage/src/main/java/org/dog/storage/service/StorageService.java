package org.dog.storage.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.dog.storage.mapper.StorageMapper;
import org.dog.storage.model.Storage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/20 08:56
 * @Description:
 */

@Slf4j
@Service
public class StorageService {

    @Resource
    private StorageMapper storageMapper;

    public boolean prepare(String productId, Integer count) {
        Storage storage = storageMapper.getStorageByProductId(productId);
        if (storage == null) {
            throw new RuntimeException("商品不存在");
        }
        storage.setFreezeCount(storage.getFreezeCount() + count);
        storage.setCount(storage.getCount() - count);
        Integer i = storageMapper.updateStorage(storage);
        log.info("{}商品库存冻结{}个", productId, count);
        return i.equals(1);
    }

    public boolean commit(BusinessActionContext actionContext) {
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        Storage storage = storageMapper.getStorageByProductId(productId);
        if (storage.getFreezeCount() < count) {
            throw new RuntimeException("库存不足，扣减失败");
        }
        storage.setFreezeCount(storage.getFreezeCount() - count);
        Integer i = storageMapper.updateStorage(storage);
        log.info("{}商品扣减库存{}个", productId, count);
        return i.equals(1);
    }

    public boolean rollback(BusinessActionContext actionContext) {
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        Storage storage = storageMapper.getStorageByProductId(productId);
        if (storage.getFreezeCount() >= count) {
            storage.setFreezeCount(storage.getFreezeCount() - count);
            storage.setCount(storage.getCount() + count);
            Integer i = storageMapper.updateStorage(storage);
            log.info("{}商品释放库存{}个", productId, count);
            return i.equals(1);
        }
        log.info("{}商品冻结的库存已释放", productId);
        return true;
    }
}
