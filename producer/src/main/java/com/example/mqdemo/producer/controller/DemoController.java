package com.example.mqdemo.producer.controller;

import com.example.mqdemo.common.utils.CommonUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lance
 */

@RestController
public class DemoController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/send/{msg}")
    public String send(@PathVariable String msg) {

        rabbitTemplate.convertAndSend("myDirectExchange", "my.direct.routing", msg);
        CommonUtils.getString();
        return "success";
    }


    @RequestMapping("/sendByFanout")
    public String sendByFanout() {
        String msg = "hello fanout";
        rabbitTemplate.convertAndSend("fanoutExchange", null, msg);
        return "success";
    }

    @RequestMapping("/sendByTopic")
    public String sendByTopic() {

        String msg = "hello topic";
        rabbitTemplate.convertAndSend("myTopicExchange", "topic.02", msg );
//        rabbitTemplate.convertAndSend("myTopicExchange", "topic.xxx", msg);
        return "success";
    }
}

