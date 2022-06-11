package com.example.bookManager.repositories;

import com.example.bookManager.domain.CustomerDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerDetail, Integer> {
}
