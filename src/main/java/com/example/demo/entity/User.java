package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    @Schema(description = "用户名称")
    private String name;

    @Schema(description = "用户年龄",maximum = "100")
    private int age;
}
