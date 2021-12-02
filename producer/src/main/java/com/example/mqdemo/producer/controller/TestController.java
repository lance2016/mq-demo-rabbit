package com.example.mqdemo.producer.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @program: mqdemo  TestController
 * @description:
 * @author: flchen
 * @create: 2021-09-27 09:54
 **/

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Value("${test.a}")
    private String a;

    @GetMapping("/a")
    public String returnA() {
        return a;
    }


    @GetMapping("/test2")
    public Long test2() throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 100, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("upload-file-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                log.info(Thread.currentThread().getName());
                longAdder.increment();
            });
        }
        Thread.sleep(3000);
        return longAdder.longValue();
    }
}
