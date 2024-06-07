package pt.isep.desofs.m1a.g1.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityHeadersInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"api.json\"");
		response.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self'; object-src 'none';");
		response.setHeader("X-Content-Type-Options", "nosniff");
		response.setHeader("Strict-Transport-Security", "max-age=15724800; includeSubdomains");
		response.setHeader("Referrer-Policy", "no-referrer");
		response.setHeader("X-Frame-Options", "DENY");
	}
}
