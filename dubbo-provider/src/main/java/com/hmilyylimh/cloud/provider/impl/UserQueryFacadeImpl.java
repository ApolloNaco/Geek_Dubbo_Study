package com.hmilyylimh.cloud.provider.impl;

import com.hmilyylimh.cloud.api.UserQueryFacade;

/**
 * 用户查询实现类逻辑
 */
public class UserQueryFacadeImpl implements UserQueryFacade {
    @Override
    public String queryUser(String name) {
        return "hello " + name + ", my name is yuki.";
    }
}
