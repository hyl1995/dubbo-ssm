package com.hyl.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestConsumerApiGeneric {
    public static void main(String[] args) throws IOException {

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubboConsumer");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        registry.setProtocol("zookeeper");

        //连接监控中心
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");

        // 泛型参数设置为GenericService
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setVersion("1.0.0");
        reference.setGroup("dubbo");
        reference.setTimeout(3000);
        reference.setMonitor(monitorConfig);

        //设置为泛化
        reference.setInterface("com.hyl.sdk.service.UserServiceBo");
        reference.setGeneric(true);

        //用com.alibaba.dubbo.rpc.service.GenericService替代所有接口引用
        GenericService userService = reference.get(); //

        // 基本类型以及Date,List,Map等不需要转换，直接调用,如果返回值为POJO也将自动转成Map
        Object result = userService.$invoke("sayHello", new String[] {"java.lang.String"},
                new Object[] {"哈哈哈"});

        System.out.println(JSON.toJSONString(result));

        //POJO参数转换为map
        Map<String, Object> map = new HashMap<>();
        map.put("class", "com.hyl.sdk.PersonImpl");
        map.put("name", "jiaduo");
        map.put("password", "password");

        result = userService.$invoke("testPojo", new String[] { "com.hyl.sdk.Person" }, new Object[] { map });
        System.out.println((result));
    }
}
