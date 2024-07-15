package com.example.demo;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisTestController {

    @Resource
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redis/get/{key}")
    public String hello(@PathVariable String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return String.valueOf(valueOperations.get(key));
    }

    @PutMapping("/redis/set")
    public String hello(@RequestParam String key, @RequestParam String value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        return value;
    }

}
