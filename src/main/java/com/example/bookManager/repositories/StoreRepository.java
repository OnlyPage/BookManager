package com.example.bookManager.repositories;

import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.domain.UserDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StoreRepository extends CrudRepository<StoreDetail, Integer> {

    @Query(value = "SELECT * FROM store_detail st WHERE st.username = :username", nativeQuery = true)
    StoreDetail findStoreByUserName(@Param("username") String username);

    List<StoreDetail> findAll();
}
