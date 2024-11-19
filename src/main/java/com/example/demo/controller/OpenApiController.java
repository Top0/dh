package com.example.demo.controller;

import com.example.demo.common.RetResult;
import com.example.demo.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "测试文档接口-增删改查", description = "测试文档")
@RestController
@RequestMapping("/user")
public class OpenApiController {
    @Operation(summary = "新建用户")
    @PostMapping()
    public RetResult<User> add(@RequestBody User user) {
        return RetResult.success(user);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public RetResult<User> delete(@PathVariable String id) {
        User user = User.builder().name(id).age(18).build();
        return RetResult.success(user);
    }

    @Operation(summary = "修改用户")
    @PutMapping()
    public RetResult<User> update(@RequestBody User user) {
        user.setAge(9999);
        return RetResult.success(user);
    }

    @Operation(summary = "获取单个用户信息")
    @GetMapping("/{id}")
    public RetResult<User> getUser(@PathVariable String id) {
        User user = User.builder().name(id).age(18).build();
        return RetResult.success(user);
    }

    @Operation(summary = "获取所有用户")
    @GetMapping()
    public RetResult<List<User>> getAllUsers() {
        return RetResult.success(new ArrayList<>());
    }

}
