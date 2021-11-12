package com.hnguigu.changgou.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AdListener {

    Object target;
    private static Logger logger = LoggerFactory.getLogger(AdListener.class);

    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = "AdQueue")
    public void receiveMessage(String message) {
        String url = "http://192.168.199.134/ad_update?position=" + message;
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, null,
                String.class);
        String body = response.getBody();
        logger.info(body);
    }
}
