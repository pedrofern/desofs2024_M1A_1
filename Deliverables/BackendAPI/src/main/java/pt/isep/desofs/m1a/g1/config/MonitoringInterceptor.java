package pt.isep.desofs.m1a.g1.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pt.isep.desofs.m1a.g1.model.monitoring.Monitoring;
import pt.isep.desofs.m1a.g1.repository.MonitoringRepository;

@Component
public class MonitoringInterceptor implements HandlerInterceptor {

	@Autowired
	private MonitoringRepository monitoringRepository;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		String uri = request.getRequestURI();
        String method = request.getMethod();
        String user = "Anonymous";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        }
        LocalDateTime dateTime = LocalDateTime.now();
        
		Monitoring monitoring = new Monitoring(uri, method, user, dateTime);
		
		monitoringRepository.save(monitoring);

	}

}
