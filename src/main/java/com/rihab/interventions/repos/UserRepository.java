package com.rihab.interventions.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rihab.interventions.entities.User;



public interface UserRepository extends JpaRepository<User, Integer> {

   // Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

void deleteById(Long userId);
}