package com.example.bookManager.repositories;

import com.example.bookManager.domain.BookDetail;
import com.example.bookManager.domain.StoreDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookDetail, Integer> {
}
