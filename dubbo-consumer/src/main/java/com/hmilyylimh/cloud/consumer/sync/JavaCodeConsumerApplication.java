package com.hmilyylimh.cloud.consumer.sync;

import com.hmilyylimh.cloud.api.UserUpdateFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.IOException;

public class JavaCodeConsumerApplication {


    public static void main(String[] args) throws IOException {
        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-consumer");
        // 设置注册中心的地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");

        // 创建引用 UserUpdateFacade 这个服务的对象
        ReferenceConfig<UserUpdateFacade> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(UserUpdateFacade.class);

        // 直接拿到引用的代理对象，然后进行远程调用
        UserUpdateFacade userUpdateFacade = referenceConfig.get();
        System.out.println(userUpdateFacade.updateUserById("G314", "yuki"));

        System.in.read();

    }
}
