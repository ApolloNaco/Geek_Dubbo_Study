package com.hmilyylimh.cloud.consumer.async;

import com.google.common.base.Stopwatch;
import com.hmilyylimh.cloud.api.AsyncUserQueryFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AsyncGetFutureResultApplication {


    public static void main(String[] args) throws Exception {


        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-consumer");
        // 设置注册中心的地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");

        // 创建引用 UserUpdateFacade 这个服务的对象
        ReferenceConfig<AsyncUserQueryFacade> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(AsyncUserQueryFacade.class);
        referenceConfig.setTimeout(12000);
        // 真正开启异步化操作，让调用方可以通过Future来拿到最终的结果
        referenceConfig.setAsync(true);
        // 直接拿到引用的代理对象，然后进行远程调用
        AsyncUserQueryFacade asyncUserQueryFacade = referenceConfig.get();
        // 既然是异步化操作获取结果，那么下面这行代码会有结果么？？？
        System.out.println("直接调:"+asyncUserQueryFacade.queryUserById("yuki"));

        // 拿到一个 Future对象，这个Future对象哪里来呢？其实是从 RpcContext 远程调用上下文中获取得到的
        System.out.println("Context get调用:"+RpcContext.getContext().getFuture().get());

        // 阻塞等待
        System.in.read();

    }
}
