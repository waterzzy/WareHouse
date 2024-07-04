package com.msb.filter;

import com.alibaba.fastjson.JSON;
import com.msb.entity.Result;
import com.msb.utils.WarehouseConstants;
import io.netty.util.internal.StringUtil;
import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

/*
自定义的登录限制过滤器
 */

public class LoginCheckFilter implements Filter {
    private StringRedisTemplate redisTemplate;

    public StringRedisTemplate getStringRedisTemplate() {
        return redisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = stringRedisTemplate;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        /*
        放行不需要拦截的请求
         */
        //得到当前请求的url接口
        String url = request.getServletPath();
        if (url.equals("/captcha/captchaImage") || url.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        //2.其他请求中 检验是否携带token
        String token = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        //请求头 与 redis中是否都含有token  如果有的化进行放行
        if (StringUtils.hasText(token) && redisTemplate.hasKey(token)) {
            chain.doFilter(request, response);
            return;
        }
        //如果没有的化 说明未登录 和token过期
        Result result = Result.err(Result.CODE_ERR_UNLOGINED,"请您先进行登录");
        String jsonStr = JSON.toJSONString(result);
        //响应给前端
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(jsonStr);
        out.flush();
        out.close();
    }
}