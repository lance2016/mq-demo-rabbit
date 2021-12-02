package com.example.mqdemo.consumer.handler;

/**
 * @program: mqdemo  DirectReceiver
 * @description:
 * @author: flchen
 * @create: 2021-08-19 15:38
 **/

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {


    @RabbitListener(queues = "myDirectQueue")
    public void process(String msg) {
        System.out.println("get number " + msg);
    }


    @RabbitListener(queues = "fanoutQueueA")
    public void processA(String msg) {
        System.out.println("fanoutQueueA " + msg);
    }


    @RabbitListener(queues = "fanoutQueueB")
    public void processB(String msg) {
        System.out.println("fanoutQueueB " + msg);
    }


    @RabbitListener(queues = "fanoutQueueC")
    public void processC(String msg) {
        System.out.println("fanoutQueueC " + msg);
    }



    @RabbitListener(queues = "myTopicQueue_01")
    public void process_01(String msg) {
        System.out.println("myTopicQueue_01 " + msg);
    }

    @RabbitListener(queues = "myTopicQueue_02")
    public void process_02(String msg) {
        System.out.println("myTopicQueue_02 " + msg);
    }

    @RabbitListener(queues = "myTopicQueue_03")
    public void process_03(String msg) {
        System.out.println("myTopicQueue_03 " + msg);
    }
}
