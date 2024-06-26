package dev.patika.tourismAgency.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("https://helpful-sundae-1cb4c5.netlify.app/")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(true);
    }

}