package com.hmilyylimh.cloud.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApplication {


    public static void main(String[] args) {
        String configPath = "classpath*:META-INF/spring/*.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ctx.refresh();

        // 接口调用

        // 阻塞等待
    }
}
