package com.hmilyylimh.cloud.provider.impl;


import com.hmilyylimh.cloud.api.UserUpdateFacade;

/**
 * 用户查询实现类逻辑
 */
public class UserUpdateFacadeImpl implements UserUpdateFacade {

    @Override
    public String updateUserById(String id, String name) {
        return "id=" + id + ",name=" + name + ", changed to newName=hmily";
    }
}
