package com.hmilyylimh.cloud.provider.impl.async;

import com.hmilyylimh.cloud.api.AsyncUserQueryFacade;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncUserQueryFacadeImpl implements AsyncUserQueryFacade {

    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 4, 0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    private int dealTime;
    @Override
    public String queryUserById(String id) {
        String resultMsg = "async id=" + id + ", my name is yuki.";

        System.out.println("下游执行了"+dealTime++);
        AsyncContext asyncContext = RpcContext.startAsync();
        // 一旦需要走异步操作，那么一定会和线程池联系起来
        poolExecutor.execute(() -> {
            asyncContext.signalContextSwitch();

            // 这里模拟执行一段耗时的业务逻辑
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            asyncContext.write(resultMsg);
        });

        return null;
    }

    @Override
    public CompletableFuture<String> queryUserByName(String name) {


        return CompletableFuture.supplyAsync(() -> {
            // 这里模拟执行一段耗时的业务逻辑
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "async name=" + name + ", my name is hmily.";
        });
    }
}
