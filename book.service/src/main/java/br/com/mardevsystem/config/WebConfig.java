package br.com.mardevsystem.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry .addMapping("/**")
                //.allowedOrigins("http://localhost:3000","http://mardevsystem.com.br")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
