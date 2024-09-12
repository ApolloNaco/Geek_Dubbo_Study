package com.hmilyylimh.cloud.api;

import java.util.concurrent.CompletableFuture;

public interface AsyncUserQueryFacade {
    public String queryUserById(String id);

    public CompletableFuture<String> queryUserByName(String name);
}
