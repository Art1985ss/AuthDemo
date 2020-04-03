package com.auth.AuthDemo.repository;

import com.auth.AuthDemo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for interactions with Question object in database. All operations for database are performed through
 * this interface (it extends JpaRepository class for enabling CRUD operations). This dependency is injected into
 * QuestionService class, so that it is not called directly.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);
}
