package com.msb.config;

import com.msb.filter.LoginCheckFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 原生Servlet的配置类:
 */
@Configuration
public class ServletConfig {

    //注入redis模板
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 注册原生Servlet的Filter
//     */
    @Bean
    public FilterRegistrationBean securityFilter(){
        //创建bean的对象  注册原生servlet 的过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //创建自定义的过滤器
        LoginCheckFilter loginCheckFilter = new LoginCheckFilter();
        //手动注入将redis 设置到过滤器中
        loginCheckFilter.setStringRedisTemplate(redisTemplate);
        filterRegistrationBean.setFilter(loginCheckFilter);
        //给过滤器蓝饥饿请求
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
