package virtusa.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                // Allow our public health check and synchronization endpoints to bypass direct session locks
                .requestMatchers(
                    "/api/v1/health/db", 
                    "/api/v1/agents/auth/sync",
                    "/api/v1/agents/profile",
                    "/api/v1/agents/listings/**",
                    "/actuator/**",
                    "/v3/api-docs/**",      // Required for documentation data
                    "/swagger-ui/**",       // Required for the UI asset layout
                    "/swagger-ui.html"      // Main browser entry page
                ).permitAll()
                .anyRequest().authenticated()                    
            )
            // Add our custom Firebase filter right before Spring's default username/password filter
            .addFilterBefore(new FirebaseAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
    }
}