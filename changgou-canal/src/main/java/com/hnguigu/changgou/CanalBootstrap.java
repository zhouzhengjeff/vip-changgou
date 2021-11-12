package com.hnguigu.changgou;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCanalClient
public class CanalBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(CanalBootstrap.class, args);
    }
}
