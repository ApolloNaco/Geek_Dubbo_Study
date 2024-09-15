package com.hmilyylimh.cloud.provider.impl;

import com.hmilyylimh.cloud.api.UserQueryFacade;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 用户查询实现类逻辑
 */
public class UserQueryFacadeImpl implements UserQueryFacade {
    @Override
    public String queryUser(String name) {
        String s = "hello " + name + ", my name is yuki. port = " + RpcContext.getContext().getLocalPort();
        System.out.println(s);
        return s;
    }
}
