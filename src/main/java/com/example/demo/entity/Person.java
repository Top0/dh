package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Person implements Serializable {
    String name;
    int age;
    Car car;
}
