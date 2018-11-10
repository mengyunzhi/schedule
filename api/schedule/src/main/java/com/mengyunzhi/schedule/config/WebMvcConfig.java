package com.mengyunzhi.schedule.config;

import com.mengyunzhi.schedule.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebMvcConfig {

    @Autowired
    AuthInterceptor authInterceptor; // 用户认证

    // 声明一个Bean，SpringMVC在启动的时候，会自动的扫描Bean，并按这个Bean的配置进行一些配置
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //添加一个映射
                //此映射允许进行CORS的地址为：http://localhost:9000
                registry.addMapping("/**").allowedOrigins("http://localhost:8200")
                        .allowedMethods("OPTIONS", "GET", "PUT", "POST", "PATCH", "DELETE");
            }

            // 拦截器
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(authInterceptor)
                        .excludePathPatterns("/User/login");
            }

        };
    }
}
