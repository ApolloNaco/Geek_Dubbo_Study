package com.hmilyylimh.cloud.consumer.async;

import com.google.common.base.Stopwatch;
import com.hmilyylimh.cloud.api.AsyncUserQueryFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AsyncJavaCodeConsumerApplication {


    public static void main(String[] args) throws IOException {


        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-consumer");
        // 设置注册中心的地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");

        // 创建引用 UserUpdateFacade 这个服务的对象
        ReferenceConfig<AsyncUserQueryFacade> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(AsyncUserQueryFacade.class);
        referenceConfig.setTimeout(500);

        Stopwatch stopwatch = Stopwatch.createStarted();
        // 直接拿到引用的代理对象，然后进行远程调用
        try{
            AsyncUserQueryFacade asyncUserQueryFacade = referenceConfig.get();
            System.out.println(asyncUserQueryFacade.queryUserById("Async cgy"));
        }catch (Exception e){
            stopwatch.stop();
            System.out.println("Catch Elapsed time: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " milliseconds");
            e.printStackTrace();
        }
        // 耗时统计
//        stopwatch.stop();
//        System.out.println("Elapsed time: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " milliseconds");

        System.in.read();
    }
}

