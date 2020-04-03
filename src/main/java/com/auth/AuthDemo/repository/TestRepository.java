package com.auth.AuthDemo.repository;

import com.auth.AuthDemo.entity.TestKC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for interactions with TestKC object in database. All operations for database are performed through
 * this interface (it extends JpaRepository class for enabling CRUD operations). This dependency is injected into
 * TestService class, so that it is not called directly.
 */
@Repository
public interface TestRepository extends JpaRepository<TestKC, Long> {
    Optional<TestKC> findByName(String name);
}
