package org.dog.server.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: Odin
 * @Date: 2023/7/5 21:25
 * @Description:这是一个bean工具类，通过这个工具类可以查询到各种Bean
 */

@Component
public class BeanUtils implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanUtils.beanFactory = beanFactory;
    }

    public static <T> T getBean(String beanName) {
        return (T) beanFactory.getBean(beanName);
    }



}
