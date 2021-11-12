package com.hnguigu.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SearchBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SearchBootstrap.class, args);
    }
}
