package com.auth.AuthDemo.service;

import java.util.List;

public interface Service_<T> {
    Long create(T t);

    Long update(T t);

    T findById(Long id);

    void deleteById(Long id);

    List<T> findAll();
}
