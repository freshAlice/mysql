package com.test.Adapter;

import com.test.interceptor.ExcelHandlerInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by Administrator on 8/14/2018.
 */
@Configuration
@EnableWebMvc
public class MvcConfigurationSupport extends WebMvcConfigurationSupport {

    public final static ExcelHandlerInterceptor excelHandlerInterceptor = new ExcelHandlerInterceptor();

    public void addInterceptors(InterceptorRegistry registry){
        super.addInterceptors(registry);
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(excelHandlerInterceptor).
                addPathPatterns("/**").
                excludePathPatterns(
                        "/index",
                        "/login",
                        "/doLogin",
                        "/logout",
                        "/register",
                        "/doRegister",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.css.map",
                        "/**/*.jpeg",
                        "/**/*.ico",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.woff",
                        "/**/*.woff2"
                );

    }

}
