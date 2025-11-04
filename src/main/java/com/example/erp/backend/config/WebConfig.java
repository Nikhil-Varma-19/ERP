package com.example.erp.backend.config;

import com.example.erp.backend.interceptors.ApiLoggerInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Autowired
    private ApiLoggerInterceptors apiLoggerInterceptors;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(apiLoggerInterceptors);
    }


}
