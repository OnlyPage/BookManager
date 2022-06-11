package com.example.bookManager.repositories;

import com.example.bookManager.domain.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public interface OrderRepository extends CrudRepository<OrderDetail, Integer> {
}
