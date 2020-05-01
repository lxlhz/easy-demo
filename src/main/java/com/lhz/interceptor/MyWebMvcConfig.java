package com.lhz.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {
    @Resource
    private AuthenticationHandlerInterceptor authenticationHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 无需拦截的接口集合
        List<String> ignorePath = new ArrayList<>();
        // swagger
        ignorePath.add("/swagger-resources/**");
        ignorePath.add("/doc.html");
        ignorePath.add("/v2/**");
        ignorePath.add("/webjars/**");

        ignorePath.add("/static/**");
        ignorePath.add("/templates/**");
        ignorePath.add("/error");

        //先拦截认证，再拦截授权
        registry.addInterceptor(authenticationHandlerInterceptor).addPathPatterns("/**").excludePathPatterns(ignorePath);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        // swagger2资源
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}