package com.auth.AuthDemo.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface Service_<T> {
    Long create(T t);

    Long update(T t);

    T findById(Long id);

    void deleteById(Long id);

    List<T> findAll();
}
