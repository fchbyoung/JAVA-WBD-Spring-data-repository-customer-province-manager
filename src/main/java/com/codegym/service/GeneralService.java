package com.codegym.service;

import com.codegym.model.Customer;

import java.util.List;

public interface GeneralService<T> {
    Iterable<T> findAll();

    Customer findById(Long id);

    Iterable<T> findByName(String name);

    void save(Customer customer);

    void delete(Long id);

}
