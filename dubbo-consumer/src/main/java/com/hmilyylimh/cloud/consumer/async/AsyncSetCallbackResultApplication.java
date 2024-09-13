package com.hmilyylimh.cloud.consumer.async;

import com.hmilyylimh.cloud.api.AsyncUserQueryFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.remoting.exchange.ResponseCallback;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.protocol.dubbo.FutureAdapter;

import java.util.concurrent.Future;

public class AsyncSetCallbackResultApplication {


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

        // 通过setback获取结果 dubbo版本必须是2.7.1才能支持这个api
        ((FutureAdapter)RpcContext.getContext().getFuture()).getFuture().setCallback(new ResponseCallback() {
            // 成功的回调
            @Override
            public void done(Object o) {
                System.out.println("set CallBack:"+o);
            }
            // 异常的回调
            @Override
            public void caught(Throwable throwable) {
                System.out.println("走到了异常:"+throwable.getLocalizedMessage());
            }
        });
        // 阻塞等待
        System.in.read();

    }
}
