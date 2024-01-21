package com.project.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springproject.model.Musician;

import jakarta.transaction.Transactional;

/**
 * This interface represents a repository for managing Musician entities.
 * It extends the JpaRepository interface, providing CRUD operations for
 * Musician objects.
 */
@Repository
@Transactional
public interface MusicianRepository extends JpaRepository<Musician, Long> {

}