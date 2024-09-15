package com.hmilyylimh.cloud.consumer.sync;

import com.hmilyylimh.cloud.api.LongTimeQueryFacade;
import com.hmilyylimh.cloud.api.UserQueryFacade;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class XmlLoadBalanceConsumerApplication {


    public static void main(String[] args) throws IOException {
        String configPath = "classpath*:META-INF/spring/loadbalance_consumer.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ctx.refresh();

        // 接口调用
        UserQueryFacade userQueryFacade = ctx.getBean(UserQueryFacade.class);
        for (int i = 0; i < 6; i++) {
            System.out.println("第"+i+"次调用:" + userQueryFacade.queryUser("cgy"));
        }
        // 阻塞等待
        System.in.read();
    }
}
