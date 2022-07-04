package com.example.bookManager.service;

import com.example.bookManager.domain.CategoryDetail;
import com.example.bookManager.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDetail getCategoryDetailById(int id)
    {
        return categoryRepository.findById(id).get();
    }
}
