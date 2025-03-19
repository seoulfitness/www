package kr.seoulfitness.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.seoulfitness.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**/**") // 보호할 URL 패턴
                .excludePathPatterns(
                    "/auth/login", 
                    "/auth/logout", 
                    "/auth/register", 
                    "/auth/find-user-id", 
                    "/auth/reset-password", 
                    "/assets/**"
                ); // 예외 URL 패턴
    }
}
