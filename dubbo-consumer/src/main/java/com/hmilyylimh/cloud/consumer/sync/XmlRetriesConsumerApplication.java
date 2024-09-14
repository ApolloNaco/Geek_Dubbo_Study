package com.hmilyylimh.cloud.consumer.sync;

import com.hmilyylimh.cloud.api.LongTimeQueryFacade;
import com.hmilyylimh.cloud.api.UserQueryFacade;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class XmlRetriesConsumerApplication {


    public static void main(String[] args) throws IOException {
        String configPath = "classpath*:META-INF/spring/retries_consumer.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ctx.refresh();

        // 接口调用
        LongTimeQueryFacade longTimeQueryFacade = ctx.getBean(LongTimeQueryFacade.class);
        System.out.println(longTimeQueryFacade.queryUser("cgy"));
        // 阻塞等待
        System.in.read();
    }
}
