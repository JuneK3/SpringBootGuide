package com.rootlab.ch14.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpInterceptor implements HandlerInterceptor {
    private final Logger LOGGER = LoggerFactory.getLogger(HttpInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 컨트롤러로 요청이 가기 전에 수행할 코드를 작성
        LOGGER.info("[preHandle] preHandle is performed");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러의 로직이 수행된 후에 view가 렌더링 되기 전에 수행할 코드를 작성
        LOGGER.info("[postHandle] postHandle is performed");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // view가 렌더링 된 후에 수행할 코드를 작성
        LOGGER.info("[afterCompletion] afterCompletion is performed");
    }
}