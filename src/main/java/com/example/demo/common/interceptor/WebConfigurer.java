package com.example.demo.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

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
        List<String> patterns = new ArrayList<>();
        patterns.add("/webjars/**");
        patterns.add("/doc.html/**");
        patterns.add("/v3/api-docs/**");
        patterns.add("/favicon.ico");

        registry.addInterceptor(new HandlerInterceptor() {
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                return false;
            }
        }).addPathPatterns("/favicon.ico");

        // order 数字越小，越优先
        registry.addInterceptor(logInterceptor).order(-1).excludePathPatterns(patterns);
        registry.addInterceptor(timeInterceptor).order(-2).excludePathPatterns(patterns);
    }
}
