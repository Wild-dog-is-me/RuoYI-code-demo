package org.dog.server.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * @Author: Odin
 * @Date: 2023/7/5 21:23
 * @Description:
 */

@Service
public class UserService implements BeanNameAware {

    public void sayHello() {
        System.out.println("hello");
    }
    @Override
    public void setBeanName(String name) {
        System.out.println("bean name is " + name);
    }
}
