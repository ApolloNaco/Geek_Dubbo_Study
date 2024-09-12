package com.hmilyylimh.cloud.provider.async;

import com.hmilyylimh.cloud.api.AsyncUserQueryFacade;
import com.hmilyylimh.cloud.provider.impl.async.AsyncUserQueryFacadeImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AsyncJavaCodeProviderApplication {
    public static void main(String[] args) throws IOException {
        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-provider");
        // 设置注册中心的地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");

        // 创建暴露UserUpdateFacade这个服务的对象
        ServiceConfig<AsyncUserQueryFacade> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(AsyncUserQueryFacade.class);
        serviceConfig.setRef(new AsyncUserQueryFacadeImpl());

        // 导出服务
        serviceConfig.export();

        System.out.println("Dubbo " + AsyncUserQueryFacade.class.getSimpleName() + " started!");
        System.out.println((new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]")).format(new Date()) + " Dubbo service server started!");

        System.in.read();
    }
}
