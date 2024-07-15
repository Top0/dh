package com.example.demo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {


    @Resource
    RedissonClient redissonClient;

    @Test
    void contextLoads() {

    }

}
