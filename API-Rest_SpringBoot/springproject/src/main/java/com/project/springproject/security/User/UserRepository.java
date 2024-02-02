package com.project.springproject.security.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents a repository for managing User entities.
 * It extends the JpaRepository interface, providing CRUD operations for User
 * entities.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return an Optional containing the found user, or an empty Optional if no
     *         user is found
     */
    Optional<User> findByUsername(String username);

}
