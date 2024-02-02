package com.project.springproject.security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.springproject.security.User.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * This class represents the configuration for the application.
 * It provides beans for the authentication manager, authentication provider,
 * password encoder, and user details service.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    /**
     * Creates and returns an instance of the AuthenticationManager.
     *
     * @param config the AuthenticationConfiguration object
     * @return the AuthenticationManager instance
     * @throws Exception if an error occurs while creating the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Creates and returns an instance of the AuthenticationProvider.
     *
     * @return the AuthenticationProvider instance
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Creates and returns an instance of the PasswordEncoder.
     *
     * @return the PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates and returns an instance of the UserDetailsService.
     *
     * @return the UserDetailsService instance
     */
    @Bean
    public UserDetailsService userDetailService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
