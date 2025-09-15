package com.projects.jslarticle.repository;


import com.projects.jslarticle.entity.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description JpaRepository - UserRepository 입니다.
 * @since 2025-09-15
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
