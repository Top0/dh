package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see org.springframework.web.servlet.DispatcherServlet
 * <p>
 * Handler 对应Controller里面的方法，所有@RequestMapping方法都可以被看作是一个Handler；
 * HandlerMapping  存的是请求路径与Handler对应关系
 * HandlerAdapter  适配器，用于执行Handler
 * <p>
 * ·根据请求找到Handler；
 * ·根据Handler找到对应的HandlerAdapter
 * ·HandlerAdapter处理Handler
 * ·处理结果
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }
}
