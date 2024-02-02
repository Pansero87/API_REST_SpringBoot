package com.project.springproject.security.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * This class represents the controller for authentication-related operations.
 * It handles the login and registration endpoints for user authentication.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Handles the login request and returns the authentication response.
     * 
     * @param request The login request containing the user's credentials.
     * @return The ResponseEntity containing the authentication response.
     */
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Handles the registration request and returns the authentication response.
     * 
     * @param request The registration request containing the user's information.
     * @return The ResponseEntity containing the authentication response.
     */
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
