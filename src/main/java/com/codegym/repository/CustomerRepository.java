package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    @Query("select c from Customer c where c.id like :id")
    Customer findById(@Param("id") Long id);

    @Query("select c from Customer c where c.lastName like :name")
    Iterable<Customer> findByName(@Param("name") String name);


}
