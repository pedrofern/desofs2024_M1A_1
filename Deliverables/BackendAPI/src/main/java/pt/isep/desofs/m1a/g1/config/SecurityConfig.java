package pt.isep.desofs.m1a.g1.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import pt.isep.desofs.m1a.g1.model.user.Role;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

	private static final String[] WHITE_LIST_URL = { "/api/v1/user/login" };
	private static final String[] ADMIN_LIST_URL = { "/api/v1/user/register", "/api/v1/user/**/assign-role" };
	private static final String[] ALL_USERS_LIST_URL = { "/api/v1/logistics/**", "/api/v1/trucks/**",
			"/api/v1/deliveries/**", "/api/v1/warehouses/**" };
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL).permitAll()
						// All authenticated users can access these endpoints
						.requestMatchers(ALL_USERS_LIST_URL)
						.hasAnyRole(Role.ADMIN.getName(), Role.WAREHOUSE_MANAGER.getName(),
								Role.FLEET_MANAGER.getName(), Role.LOGISTICS_MANAGER.getName(), Role.OPERATOR.getName())
						// Only ADMIN can access these endpoints
						.requestMatchers(ADMIN_LIST_URL).hasAnyRole(Role.ADMIN.getName()).anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
				.authenticationProvider(authenticationProvider)
				.exceptionHandling(e -> e.authenticationEntryPoint((request, response, authException) -> response
						.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage())))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(logout -> logout.logoutUrl("/api/v1/user/logout").addLogoutHandler(logoutHandler)
						.logoutSuccessHandler(
								(request, response, authentication) -> SecurityContextHolder.clearContext()));

		return http.build();
	}
}
