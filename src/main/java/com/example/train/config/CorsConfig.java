package com.example.train.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//全局配置类，配置跨域请求
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Value("${recPath}")
    private String recPath;

    @Value("${swiPath}")
    private String swiPath;

    @Value("${imgPath}")
    private String imgPath;

    @Value(("${bookPath}"))
    private String bookPath;

//    @Value("${videoPath}")
//    private String videoPath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*
         * 预访问的路径
         * 请求来源
         * 方法
         * 允许携带tocken等内容
         * 最大响应时间
         */
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //D:/OnlineTraining/src/main/resources/files/book/
        registry.addResourceHandler("/a/**").addResourceLocations("file:"+bookPath);
        //D:\OnlineTraining\src\main\resources\files\cards\
        registry.addResourceHandler("/ide/**").addResourceLocations("file:"+imgPath);
        registry.addResourceHandler("/rec/**").addResourceLocations("file:"+recPath);
        registry.addResourceHandler("/swiper/**").addResourceLocations("file:"+swiPath);
//        registry.addResourceHandler("/video/**").addResourceLocations("file:"+videoPath);
        super.addResourceHandlers(registry);
    }
}