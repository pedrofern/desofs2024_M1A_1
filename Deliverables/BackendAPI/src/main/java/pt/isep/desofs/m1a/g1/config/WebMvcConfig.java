package pt.isep.desofs.m1a.g1.config;

import io.github.bucket4j.grid.GridBucketState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.cache.Cache;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private Cache<String, GridBucketState> cache;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RateLimitInterceptor(cache)).addPathPatterns("/api/v1/logistics/**");
    }
}
