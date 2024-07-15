package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Car implements Serializable {
    String carType;
    double price;
}
