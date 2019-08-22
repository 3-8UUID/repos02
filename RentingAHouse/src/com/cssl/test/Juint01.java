package com.cssl.test;

import com.cssl.controller.HouseController;
import com.cssl.pojo.User;
import com.cssl.service.HouseService;
import com.cssl.service.UserService;
import com.cssl.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Juint01 {
    private ApplicationContext context = new ClassPathXmlApplicationContext("com/cssl/config/applicationContext.xml");
    @Autowired
    private UserServiceImpl userService;

    @Test
    void test01() {
        UserService us = context.getBean(UserService.class);
        User user = us.userLogin(new User("123", "123"));
        System.out.println(user);

    }

    @Test
    void test02() {
        HouseService bean = context.getBean(HouseService.class);
        bean.getHType().forEach(houseType -> System.out.println(houseType));
    }

    @Test
    void test03() {
        HouseController hc = new HouseController();
    }
}
