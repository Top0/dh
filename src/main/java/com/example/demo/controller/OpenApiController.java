package com.example.demo.controller;

import com.example.demo.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "测试文档接口-增删改查", description = "测试文档")
@RestController
public class OpenApiController {
    @Operation(summary = "获取所有品牌列表", description = "需要登录后访问")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getBrandList() {
        return new ArrayList<>();
    }
}
