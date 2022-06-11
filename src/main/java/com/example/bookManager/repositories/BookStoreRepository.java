package com.example.bookManager.repositories;

import com.example.bookManager.domain.BookStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStoreRepository extends CrudRepository<BookStore, Integer> {
}
