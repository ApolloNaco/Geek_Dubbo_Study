package com.hmilyylimh.cloud.provider.sync;



import com.hmilyylimh.cloud.api.UserUpdateFacade;
import com.hmilyylimh.cloud.provider.impl.UserUpdateFacadeImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JavaCodeProviderApplication {
    public static void main(String[] args) throws IOException {
        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-provider");
        // 设置注册中心的地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");

        // 创建暴露UserUpdateFacade这个服务的对象
        ServiceConfig<UserUpdateFacade> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(UserUpdateFacade.class);
        serviceConfig.setRef(new UserUpdateFacadeImpl());

        // 导出服务
        serviceConfig.export();

        System.out.println("Dubbo " + UserUpdateFacade.class.getSimpleName() + " started!");
        System.out.println((new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]")).format(new Date()) + " Dubbo service server started!");

        System.in.read();
    }
}
