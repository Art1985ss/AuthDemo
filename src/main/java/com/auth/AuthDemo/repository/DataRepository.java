package com.auth.AuthDemo.repository;

import com.auth.AuthDemo.entity.Data;
import com.auth.AuthDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataRepository extends JpaRepository<Data, Long> {
    @Query(value = "select d.data_text from user_data d where user_id=1", nativeQuery = true)
    List<Data> findByStudent(Student student);
}
