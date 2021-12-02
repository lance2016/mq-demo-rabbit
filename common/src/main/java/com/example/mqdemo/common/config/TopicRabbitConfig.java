package com.example.mqdemo.common.config;

/**
 * @program: mqdemo  TopicRabbitConfig
 * @description:
 * @author: flchen
 * @create: 2021-08-19 16:02
 **/

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    // 交换机
    @Bean
    public TopicExchange myTopicExchange() {
        return new TopicExchange("myTopicExchange", true, false);
    }

    // ----- 队列 -----
    @Bean
    public Queue myTopicQueue_01() {
        return new Queue("myTopicQueue_01", true);
    }

    @Bean
    public Queue myTopicQueue_02() {
        return new Queue("myTopicQueue_02", true);
    }

    @Bean
    public Queue myTopicQueue_03() {
        return new Queue("myTopicQueue_03", true);
    }

    /**
     * 绑定路由键为topic.01
     */
    @Bean
    public Binding binding_01() {
        return BindingBuilder.bind(myTopicQueue_01()).to(myTopicExchange()).with("topic.01");
    }

    /**
     * 绑定路由键为topic.#规则
     */
    @Bean
    public Binding binding_02() {
        return BindingBuilder.bind(myTopicQueue_02()).to(myTopicExchange()).with("topic.02");
    }

    /**
     * 绑定路由键为topic.*规则
     */
    @Bean
    public Binding binding_03() {
        return BindingBuilder.bind(myTopicQueue_03()).to(myTopicExchange()).with("topic.*");
    }
}
