package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(indexes = {@Index(columnList = "name", name = "test1_index"),
        @Index(columnList = "name,age", name = "test2_index")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, columnDefinition = "varchar(20) comment '用户名'")
    String name;

    int age;

    @Column(nullable = true, length = 50, columnDefinition = "varchar(20) comment '邮箱'")
    String email;

    @Transient
    String address;

    LocalDate birthday;
}
