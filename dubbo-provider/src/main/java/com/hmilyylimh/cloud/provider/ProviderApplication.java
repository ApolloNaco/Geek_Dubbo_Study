package com.hmilyylimh.cloud.provider;



import org.apache.dubbo.container.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;


public class ProviderApplication {
    public static void main(String[] args) throws InterruptedException {
//        Main.main(new String[]{"spring","log4j"});
//        Main.main(new String[]{});

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        context.start();
        new CountDownLatch(1).await();
    }
}
