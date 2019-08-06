package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Province;

public interface CustomerService {
    Iterable<Customer> findAll();

    Customer findById(Long id);

    Iterable<Customer> findByName(String name);

    void save(Customer customer);

    void remove(Long id);

    Iterable<Customer> findAllByProvince(Province province);
}