package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalTime;

@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {
    private final ThreadLocal<LocalTime> start = new ThreadLocal<>();
    private final ThreadLocal<LocalTime> end = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LocalTime s = LocalTime.now();
        start.set(s);
        log.info("请求开始时间：" + s);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LocalTime e = LocalTime.now();
        end.set(e);
        log.info("请求结束时间：" + e);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("耗时：{} 毫秒", Duration.between(start.get(), end.get()).getNano() / 1000000);
    }
}
