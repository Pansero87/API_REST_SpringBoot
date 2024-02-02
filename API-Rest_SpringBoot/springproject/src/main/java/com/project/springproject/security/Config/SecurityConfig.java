package com.project.springproject.security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.springproject.security.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

/**
 * This class represents the configuration for the security of the application.
 * It provides the necessary settings for authentication and authorization.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    /**
     * Configures the security filter chain for the application.
     * It disables CSRF protection, defines the authorization rules,
     * sets the session management policy, and adds the necessary filters.
     * 
     * @param http the HttpSecurity object to configure the security filter chain
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(
                        sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
