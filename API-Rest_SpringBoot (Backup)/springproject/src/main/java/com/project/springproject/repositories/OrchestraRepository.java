package com.project.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springproject.model.Orchestra;

import jakarta.transaction.Transactional;

/**
 * This interface represents a repository for managing Orchestra entities.
 * It extends the JpaRepository interface, providing CRUD operations for
 * Orchestra entities.
 */
@Repository
@Transactional
public interface OrchestraRepository extends JpaRepository<Orchestra, Long> {

}
