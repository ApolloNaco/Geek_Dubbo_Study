package com.hmilyylimh.cloud.consumer.sync;

import com.hmilyylimh.cloud.api.UserQueryFacade;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class XmlCheckConsumerApplication {


    public static void main(String[] args) throws IOException {
        String configPath = "classpath*:META-INF/spring/check_consumer.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ctx.refresh();

        // 接口调用
        UserQueryFacade userQueryFacade = ctx.getBean(UserQueryFacade.class);
//        System.out.println(userQueryFacade.queryUser("cgy"));
        // 阻塞等待
        System.in.read();
    }
}
