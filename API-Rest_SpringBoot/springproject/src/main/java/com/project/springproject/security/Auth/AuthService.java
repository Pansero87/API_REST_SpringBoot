package com.project.springproject.security.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.springproject.security.Jwt.JwtService;
import com.project.springproject.security.User.Role;
import com.project.springproject.security.User.User;
import com.project.springproject.security.User.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * This class provides authentication and authorization services for the
 * application.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        /**
         * Authenticates the user with the provided credentials and generates a JWT
         * token.
         * 
         * @param request The login request containing the username and password.
         * @return The authentication response containing the JWT token.
         */
        public AuthResponse login(LoginRequest request) {
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                request.getPassword()));
                UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
                String token = jwtService.getToken(user);
                return AuthResponse.builder()
                                .token(token)
                                .build();

        }

        /**
         * Registers a new user with the provided information and generates a JWT token.
         * 
         * @param request The register request containing the user details.
         * @return The authentication response containing the JWT token.
         */
        public AuthResponse register(RegisterRequest request) {
                User user = User.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .firstname(request.getFirstname())
                                .lastname(request.lastname)
                                .email(request.getEmail())
                                .role(Role.USER)
                                .build();

                userRepository.save(user);

                return AuthResponse.builder()
                                .token(jwtService.getToken(user))
                                .build();

        }

}
