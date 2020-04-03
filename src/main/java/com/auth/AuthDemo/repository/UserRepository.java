package com.auth.AuthDemo.repository;

import com.auth.AuthDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for interactions with User object in database. All operations for database are performed through
 * this interface (it extends JpaRepository class for enabling CRUD operations). This dependency is injected into
 * UserService class, so that it is not called directly.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
