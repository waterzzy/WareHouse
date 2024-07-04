package com.msb.filter;

import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;

/*
自定义的登录限制过滤器
 */

public class LoginCheckFilter implements Filter {
    private StringRedisTemplate stringRedisTemplate;

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        /*
        放行不需要拦截的请求
         */
        //得到当前请求的url接口
        String url = request.getServletPath();
        if (url.equals("/captcha/captchaImage")|| url.equals("/login")){
            chain.doFilter(request,response);
            return;
        }

    }
}