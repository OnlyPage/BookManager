package com.example.bookManager.repositories;

import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.domain.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<StoreDetail, Integer> {
}
