package com.mengyunzhi.SpringMvcStudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author panjie on 2017/12/30
 */
@Component
public class WebMvcConfig {
    // 声明一个Bean. SpringMVC在启动的时候，会自动的打锚这个BEAN，并按这个BEAN的配置进行一些配置
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 添加一个映射 /Teacher
                // 此映射允许进行CORS的地址为：http://localhost:9000
                registry.addMapping("/**").allowedOrigins("http://localhost:9000")
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
            }
        };
    }
}
