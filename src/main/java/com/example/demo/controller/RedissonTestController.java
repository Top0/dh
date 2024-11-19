package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.entity.Person;
import jakarta.annotation.Resource;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController("/redis")
public class RedissonTestController {

    @Resource
    RedissonClient redissonClient;

    @GetMapping("/list")
    public Set<Person> queryList() {
        RScoredSortedSet<Person> people = redissonClient.getScoredSortedSet("demo-test");
        return people.stream().collect(Collectors.toSet());
    }

    @PutMapping("/put")
    public Set<Person> putList() {
        RScoredSortedSet<Person> people = redissonClient.getScoredSortedSet("demo-test");

        Person person = Person.builder().name("wang").age(18).car(Car.builder().carType("wang-car").price(11.1).build()).build();
        Person person1 = Person.builder().name("wang1").age(181).car(Car.builder().carType("wang-car1").price(12.1).build()).build();
        Person person2 = Person.builder().name("wang2").age(182).car(Car.builder().carType("wang-car2").price(13.1).build()).build();
        Person person3 = Person.builder().name("wang3").age(183).car(Car.builder().carType("wang-car3").price(14.1).build()).build();

        people.add(person.getAge(), person);
        people.add(person1.getAge(), person1);
        people.add(person2.getAge(), person2);
        people.add(person3.getAge(), person3);

        return people.stream().collect(Collectors.toSet());
    }


    @GetMapping("/getone")
    public Person get() {
        RScoredSortedSet<Person> people = redissonClient.getScoredSortedSet("demo-test");
        return people.pollFirst();
    }
}
