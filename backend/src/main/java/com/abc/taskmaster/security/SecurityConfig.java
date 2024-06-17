package com.abc.taskmaster.security;

import com.abc.taskmaster.employee.Role;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Set unauthorized requests exception handler
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(
                (request, response, ex) -> {
                    response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            ex.getMessage()
                    );
                }
        ));
        return http
                .cors(Customizer.withDefaults()) // Enable CORS and disable CSRF
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request // Set permissions on requests
                    .requestMatchers("api/v1/employees/hello").hasAuthority(Role.USER.name())
                    .requestMatchers("api/v1/admin/**").hasAuthority("ADMIN")
                    .requestMatchers("api/v1/employees/**").hasAuthority("USER")
                    .requestMatchers("api/v1/tasks/**").hasAuthority("USER")
                    .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) // Set session management to stateless
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


    /*@Bean
    public UserDetailsService userDetailsService() {
        return username -> employeeRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }*/

}
