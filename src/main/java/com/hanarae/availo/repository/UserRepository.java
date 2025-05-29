package com.hanarae.availo.repository;

import com.hanarae.availo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
