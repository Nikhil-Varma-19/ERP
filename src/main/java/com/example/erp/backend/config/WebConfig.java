package com.example.erp.backend.config;

import com.example.erp.backend.interceptors.ApiLoggerInterceptors;
import com.example.erp.backend.utilizs.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Autowired
    private ApiLoggerInterceptors apiLoggerInterceptors;
    @Autowired
    private FilePath filePath;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(apiLoggerInterceptors);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(filePath.getGetFile()+"/**")
                .addResourceLocations("file:"+filePath.getUploadFile()+"/");
    }


}
