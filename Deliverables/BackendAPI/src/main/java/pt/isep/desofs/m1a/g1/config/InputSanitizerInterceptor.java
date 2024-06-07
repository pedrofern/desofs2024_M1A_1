package pt.isep.desofs.m1a.g1.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InputSanitizerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// Sanitize request parameters
		request.getParameterMap().forEach((key, values) -> {
            for (int i = 0; i < values.length; i++) {
                if (!isValid(values[i])) {
                    throw new IllegalArgumentException("Invalid input: " + values[i]);
                }
            }
        });

        // Sanitize headers
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            String headerValue = request.getHeader(headerName);
            if (headerValue != null) {
                if (!isValid(headerValue)) {
                    throw new IllegalArgumentException("Invalid header value: " + headerValue);
                }
            }
        });

		// Continue the request handling
		return true;
	}

	private boolean isValid(String input) {
        return !InputSanitizer.containsMaliciousContent(input);
    }

}
