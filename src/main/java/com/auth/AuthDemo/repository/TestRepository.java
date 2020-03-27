package com.auth.AuthDemo.repository;

import com.auth.AuthDemo.entity.TestKC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<TestKC, Long> {
    Optional<TestKC> findByName(String name);
}
