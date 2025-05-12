package com.siscon.employee.infrastructure.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collections;

@Slf4j
@Component
public class HeaderLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse servletResponse,
                             Object handler) throws Exception {

        log.info("Headers recibidos:");
        Collections.list(httpServletRequest.getHeaderNames())
                .forEach(header -> log.info("header: {} = {}", header, httpServletRequest.getHeader(header)));

        return true;
    }
}
