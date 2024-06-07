package pt.isep.desofs.m1a.g1.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.github.bucket4j.grid.GridBucketState;
import io.github.bucket4j.grid.ProxyManager;
import io.github.bucket4j.grid.jcache.JCache;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.cache.Cache;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

public class RateLimitInterceptor implements HandlerInterceptor {

    private final Cache<String, GridBucketState> cache;
    private final ProxyManager<String> buckets;

    public RateLimitInterceptor(Cache<String, GridBucketState> cache) {
        this.cache = cache;
        this.buckets = Bucket4j.extension(JCache.class).proxyManagerForCache(cache);
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("X-api-key");
        if (apiKey == null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing Header: X-api-key");
            return false;
        }

        Bucket bucket = buckets.getProxy(apiKey, () -> {
            Bandwidth limit = Bandwidth.classic(1000, Refill.intervally(1000, Duration.ofMinutes(1)));
            return Bucket4j.configurationBuilder().addLimit(limit).build();
        });

        if (bucket.tryConsume(1)) {
            return true;
        } else {
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "You have exceeded your API call rate limit.");
            return false;
        }
    }
}