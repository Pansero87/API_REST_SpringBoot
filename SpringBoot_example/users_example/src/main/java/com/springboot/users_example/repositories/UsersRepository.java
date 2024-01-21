package com.springboot.users_example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.users_example.model.UserEntity;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {

}
