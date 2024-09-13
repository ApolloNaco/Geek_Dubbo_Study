package com.hmilyylimh.cloud.consumer.async;

import com.hmilyylimh.cloud.api.AsyncUserQueryFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.remoting.exchange.ResponseCallback;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.protocol.dubbo.FutureAdapter;

import java.util.concurrent.CompletableFuture;

public class AsyncGetCompletableFutureResultApplication {


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
        asyncUserQueryFacade.queryUserById("G314");

        CompletableFuture<String> completableFuture = RpcContext.getContext().getCompletableFuture();
        String s = completableFuture.get();
        System.out.println("执行结果:"+s);
        // 阻塞等待
        System.in.read();

    }
}
