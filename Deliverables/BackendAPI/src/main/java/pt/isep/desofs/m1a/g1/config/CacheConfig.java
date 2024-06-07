package pt.isep.desofs.m1a.g1.config;

import io.github.bucket4j.grid.GridBucketState;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager customCacheManager() {
        CachingProvider provider = Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
        return provider.getCacheManager();
    }

    @Bean
    public Cache<String, GridBucketState> bucketCache(CacheManager cacheManager) {
        MutableConfiguration<String, GridBucketState> configuration = new MutableConfiguration<>();
        return cacheManager.createCache("bucketCache", configuration);
    }
}

