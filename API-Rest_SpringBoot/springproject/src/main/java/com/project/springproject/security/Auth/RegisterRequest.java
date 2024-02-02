package com.project.springproject.security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a registration request for a user.
 * This class contains the user's username, password, first name, last name, and
 * email.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String firstname;
    String lastname;
    String email;
}
