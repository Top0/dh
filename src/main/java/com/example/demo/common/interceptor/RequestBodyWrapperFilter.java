package com.example.demo.common.interceptor;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class RequestBodyWrapperFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest myRequestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            myRequestWrapper = new RepeatableHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        }
        if (myRequestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.info("使用可重复读取请求体包装类");
            filterChain.doFilter(myRequestWrapper, servletResponse);
        }
    }

}

