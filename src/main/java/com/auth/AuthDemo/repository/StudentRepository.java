package com.auth.AuthDemo.repository;

import com.auth.AuthDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
