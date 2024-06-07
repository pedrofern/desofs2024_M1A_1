package pt.isep.desofs.m1a.g1.config;

import io.github.bucket4j.Bucket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApplicationScoped
public class CacheConfig {

    @Produces
    public ConcurrentMap<String, Bucket> rateLimitCache() {
        return new ConcurrentHashMap<>();
    }
}


