package com.project.springproject.security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a login request object.
 * This class is used to encapsulate the username and password for
 * authentication purposes.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String username;
    String password;
}
