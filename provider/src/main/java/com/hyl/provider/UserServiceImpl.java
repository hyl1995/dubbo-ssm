package com.hyl.provider;

import com.alibaba.fastjson.JSON;
import com.hyl.sdk.service.Person;
import com.hyl.sdk.service.UserServiceBo;

public class UserServiceImpl implements UserServiceBo {
    @Override
    public String sayHello(String name) {
        //让当前当前线程休眠2s
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return name;
    }

    @Override
    public String testPojo(Person person) {
        return JSON.toJSONString(person);
    }
}
