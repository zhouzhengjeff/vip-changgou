package com.hnguigu.changgou.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String AD_QUEUE_NAME = "AdQueue";
    public static final String SPU_PULL_QUEUE_NAME = "SpuPullQueue";

    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue adQueue() {
        return QueueBuilder.durable(AD_QUEUE_NAME).build();
    }

    @Bean
    public Queue spuPullQueue() {
        return QueueBuilder.durable(SPU_PULL_QUEUE_NAME).build();
    }

}
