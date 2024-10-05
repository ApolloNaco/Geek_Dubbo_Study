package com.hmilyylimh.cloud.provider.impl;

import com.hmilyylimh.cloud.api.LongTimeQueryFacade;
import com.hmilyylimh.cloud.api.UserQueryFacade;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.threadpool.ThreadPool;
import org.apache.dubbo.rpc.RpcContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * 用户查询实现类逻辑
 */
public class LongTimeQueryFacadeImpl implements LongTimeQueryFacade {
    @Override
    public String queryUser(String name) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String s = "LongTimeQueryFacadeImpl hello " + name + ", my name is yuki. port:" + RpcContext.getContext().getLocalPort();
        System.out.println(s);
        return s;
    }

    @Override
    public String queryUserDumpStack(String name) {
        ThreadPool defaultExtension = ExtensionLoader.getExtensionLoader(ThreadPool.class).getDefaultExtension();

        Map<String, String> map = new HashMap<>();
        map.put("dump.directory","E:\\program\\code\\Geek_Dubbo_Study");
        map.put("threadpool","fixed");
        URL url = new URL("","",0,map);

        Executor executor = defaultExtension.getExecutor(url);

        for (int i = 0; i < 300; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        String s = "LongTimeQueryFacadeImpl hello " + name + ", my name is yuki. port:" + RpcContext.getContext().getLocalPort();
        System.out.println(s);
        return s;
    }
}
