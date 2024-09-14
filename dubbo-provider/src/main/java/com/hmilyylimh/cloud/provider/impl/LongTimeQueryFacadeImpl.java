package com.hmilyylimh.cloud.provider.impl;

import com.hmilyylimh.cloud.api.LongTimeQueryFacade;
import com.hmilyylimh.cloud.api.UserQueryFacade;
import org.apache.dubbo.rpc.RpcContext;

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
}
