package com.hnguigu.changgou.config;

import com.hnguigu.changgou.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdWorkerConfig {

    @Value("${workerId}")
    private long workerId;

    @Value("${datacenterId}")
    private long datacenterId;

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(this.workerId, this.datacenterId);
    }
}
