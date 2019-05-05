package com.hyl.consumer;

import com.hyl.sdk.UserServiceBo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:consumer.xml" });

        final UserServiceBo demoService = (UserServiceBo) context.getBean("userService");

        System.out.println(demoService.sayHello("哈哈哈"));
    }
}
