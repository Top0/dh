package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 多个 Interceptor 的执行顺序
 * Interceptor1 preHandle
 * Interceptor2 preHandle
 * ...
 * ...
 * Interceptor2 postHandle
 * Interceptor1 postHandle
 * ...
 * Interceptor2 afterCompletion
 * Interceptor1 afterCompletion
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    LogInterceptor logInterceptor;
    @Autowired
    TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // order 数字越小，越优先
        registry.addInterceptor(logInterceptor).order(-1);
        registry.addInterceptor(timeInterceptor).order(-2);
    }
}
