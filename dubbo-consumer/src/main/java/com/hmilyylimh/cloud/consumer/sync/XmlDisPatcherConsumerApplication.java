package com.hmilyylimh.cloud.consumer.sync;

import com.hmilyylimh.cloud.api.LongTimeQueryFacade;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 线程模型设置，启动类
 */
public class XmlDisPatcherConsumerApplication {


    public static void main(String[] args) throws IOException {
        String configPath = "classpath*:META-INF/spring/dispatcher_consumer.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ctx.refresh();

        // 接口调用
        LongTimeQueryFacade longTimeQueryFacade = ctx.getBean(LongTimeQueryFacade.class);
        System.out.println(longTimeQueryFacade.queryUser("cgy"));
        // 阻塞等待
        System.in.read();
    }
}
