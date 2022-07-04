package com.example.bookManager.repositories;

import com.example.bookManager.domain.CategoryDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryDetail,Integer> {
}
