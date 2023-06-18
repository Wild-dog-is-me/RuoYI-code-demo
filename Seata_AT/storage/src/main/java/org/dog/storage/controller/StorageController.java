package org.dog.storage.controller;

import org.dog.common.RespBean;
import org.dog.storage.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/6/16 20:59
 * @Description:
 */

@RestController
public class StorageController {

    @Resource
    StorageService storageService;

    @PostMapping("/deduct")
    public RespBean deduct(String productId, Integer count) {
        if (storageService.deduct(productId, count)) {
            return RespBean.ok("扣库存成功");
        }
        return RespBean.error("扣库存失败");
    }
}
