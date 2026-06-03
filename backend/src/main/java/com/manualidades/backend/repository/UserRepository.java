package com.manualidades.backend.repository;

import com.manualidades.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
    User findByEmail(String email);
}