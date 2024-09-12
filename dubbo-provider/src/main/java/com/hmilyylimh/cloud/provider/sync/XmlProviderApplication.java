package com.hmilyylimh.cloud.provider.sync;



import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;


public class XmlProviderApplication {
    public static void main(String[] args) throws InterruptedException {
//        Main.main(new String[]{"spring","log4j"});
//        Main.main(new String[]{});

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        context.start();
        new CountDownLatch(1).await();
    }
}
