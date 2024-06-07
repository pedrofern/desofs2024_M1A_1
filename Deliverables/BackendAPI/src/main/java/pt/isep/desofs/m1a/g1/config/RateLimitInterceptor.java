package pt.isep.desofs.m1a.g1.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.Duration;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final ConcurrentMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("Accept-Encoding");
        if (apiKey == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing Header: Accept-Encoding");
            return false;
        }

        Bucket bucket = buckets.computeIfAbsent(apiKey, k -> createNewBucket());

        if (bucket.tryConsume(1)) {
            return true;
        } else {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "You have exceeded your API call rate limit.");
            return false;
        }
    }

    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(1000, Refill.intervally(1000, Duration.ofMinutes(1)));
        return Bucket4j.builder().addLimit(limit).build();
    }
}

