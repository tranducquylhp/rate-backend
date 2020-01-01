package com.codegym.rate.service;

import java.util.List;

public interface GeneralService<T> {
    List<T> findAll();
    void save(T t);
    T findById(Long id);
}
