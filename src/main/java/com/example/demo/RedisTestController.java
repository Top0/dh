package com.example.demo;

import com.example.demo.entity.Car;
import com.example.demo.entity.Person;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.ChannelTopic;
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

    //http://127.0.0.1:8080/redis/pub?key=chat.1111&value=value12
    @PutMapping("/redis/pub")
    public String helloPub(@RequestParam String key, @RequestParam String value) {
        String channel = new ChannelTopic(key).getTopic();
        redisTemplate.convertAndSend(channel, value);
        return value;
    }

    @GetMapping("/person/{key}")
    public Object queryPerson(@PathVariable String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @PutMapping("/person")
    public Object addPerson(@RequestParam String key, @RequestParam int value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Person person = Person.builder().age(value).name(key).car(Car.builder().carType("cat-t").price(value).build()).build();
        valueOperations.set(key, person);
        return person;
    }

}
