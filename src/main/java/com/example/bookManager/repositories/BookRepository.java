package com.example.bookManager.repositories;

import com.example.bookManager.domain.BookDetail;
import com.example.bookManager.domain.StoreDetail;
import com.example.bookManager.domain.UserDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookDetail, Integer> {

    List<BookDetail> findAll();

    List<BookDetail> findByStoreDetailId(int storeDetailId);

    @Query(value = "SELECT * FROM book_detail st WHERE st.store_detail_id = :store_detail_id", nativeQuery = true)
    List<BookDetail> findBookByIdStore(@Param("store_detail_id") Integer username);
}
