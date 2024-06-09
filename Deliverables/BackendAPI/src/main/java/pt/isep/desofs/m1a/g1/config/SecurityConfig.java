package pt.isep.desofs.m1a.g1.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import pt.isep.desofs.m1a.g1.model.user.Role;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${frontend.endpoint}")
    private String allowedOrigins;
    private static final String[] WHITE_LIST_URL = {"/api/v1/user/login/**"};
    private static final String[] ADMIN_LIST_URL = {"/api/v1/user/register", "/api/v1/user/*/assign-role"};
    private static final String[] ALL_USERS_LIST_URL = {"/api/v1/user/*", "/api/v1/users"};
    private static final String[] WAREHOUSE_LIST_URL = {"/api/v1/warehouses/**", "/api/v1/deliveries/**",};
    private static final String[] LOGISTICS_LIST_URL = {"/api/v1/logistics/**"};
    private static final String[] FLEET_LIST_URL = {"/api/v1/trucks/**"};

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Autowired
    private SecurityHeadersInterceptor securityHeadersInterceptor;
    @Autowired
    private InputSanitizerInterceptor inputSanitizerInterceptor;
    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;
    @Autowired
    private MonitoringInterceptor monitoringInterceptor;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        // All authenticated users can access these endpoints
                        .requestMatchers(ALL_USERS_LIST_URL).hasAnyRole(Role.ADMIN.getName(), Role.WAREHOUSE_MANAGER.getName(),
                                Role.FLEET_MANAGER.getName(), Role.LOGISTICS_MANAGER.getName(), Role.OPERATOR.getName())
                        // Only ADMIN can access these endpoints
                        .requestMatchers(ADMIN_LIST_URL).hasAnyRole(Role.ADMIN.getName())
                        // Only ADMIN and WAREHOUSE_MANAGER can access GET requests for pdf
                        .requestMatchers(HttpMethod.GET, "/api/v1/deliveries/delivery-plan/pdf/**").hasAnyRole(Role.ADMIN.getName(), Role.WAREHOUSE_MANAGER.getName())
                        // Only OPERATOR can access GET requests
                        .requestMatchers(HttpMethod.GET, "/**").hasAnyRole(Role.ADMIN.getName(), Role.WAREHOUSE_MANAGER.getName(),
                                Role.FLEET_MANAGER.getName(), Role.LOGISTICS_MANAGER.getName(), Role.OPERATOR.getName())
                        // Only WAREHOUSE_MANAGER can access these endpoints
                        .requestMatchers(WAREHOUSE_LIST_URL).hasAnyRole(Role.ADMIN.getName(), Role.WAREHOUSE_MANAGER.getName())
                        // Only LOGISTICS_MANAGER can access these endpoints
                        .requestMatchers(LOGISTICS_LIST_URL).hasAnyRole(Role.ADMIN.getName(), Role.LOGISTICS_MANAGER.getName())
                        // Only FLEET_MANAGER can access these endpoints
                        .requestMatchers(FLEET_LIST_URL).hasAnyRole(Role.ADMIN.getName(), Role.FLEET_MANAGER.getName())
                        .anyRequest().hasRole(Role.ADMIN.getName())
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/api/v1/user/logout").addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()))
                .exceptionHandling(e -> e
                        .authenticationEntryPoint((request, response, authException) -> response
                                .sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()))
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, accessDeniedException.getMessage());
                        }));

        return http.build();
    }

    @Bean
    public WebMvcConfigurer webConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins(allowedOrigins)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
                        .allowCredentials(true);
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
            	registry.addInterceptor(monitoringInterceptor);
                registry.addInterceptor(securityHeadersInterceptor);
                registry.addInterceptor(inputSanitizerInterceptor);
                registry.addInterceptor(rateLimitInterceptor);

            }
        };
    }

}
