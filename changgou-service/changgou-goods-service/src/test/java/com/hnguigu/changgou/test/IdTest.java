package com.hnguigu.changgou.test;

import com.hnguigu.changgou.common.util.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IdTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Test
    public void testUUID() {
        String id = UUID.randomUUID().toString();
        System.out.println(id);
    }

    @Test
    public void testRedis() {
//        Object value = this.redisTemplate.boundValueOps("k1").get();
//        System.out.println(value);

        /*Object value = this.redisTemplate.opsForValue().get("key1");
        System.out.println(value);*/

        Set keys = this.redisTemplate.keys("*");
        keys.stream().forEach(System.out::println);

        /*this.redisTemplate.opsForValue().set("k2", "v2");
        Object k2 = this.redisTemplate.opsForValue().get("k2");
        System.out.println(k2);

        this.redisTemplate.opsForValue().set("k1", "v1");
        Object k1 = this.redisTemplate.opsForValue().get("k1");
        System.out.println(k1);*/


        /*this.redisTemplate.opsForValue().set("k3", "v3");
        Object k3 = this.redisTemplate.opsForValue().get("k3");
        System.out.println(k3);*/
    }

    @Test
    public void testIdWorker() {
        System.out.println(this.idWorker.nextId());
    }
}
