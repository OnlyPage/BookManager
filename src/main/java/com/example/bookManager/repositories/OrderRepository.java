package com.example.bookManager.repositories;

import com.example.bookManager.domain.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderRepository extends CrudRepository<OrderDetail, Integer> {

    List<OrderDetail> findAll();

    @Query(value = "SELECT * FROM order_detail od WHERE od.customer_detail = :customerName", nativeQuery = true)
    List<OrderDetail> findOrderByCustomerName(@Param("customerName") String customerName);

    @Query(value = "SELECT * FROM order_detail od WHERE od.store_name = :storeName", nativeQuery = true)
    List<OrderDetail> findOrderByStoreName(@Param("storeName") String storeName);
}
