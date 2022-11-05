package com.storepc.services.impls;

import com.storepc.models.Category;
import com.storepc.payloads.CategoryDto;
import com.storepc.repositories.CategoryRepository;
import com.storepc.services.CategoryService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long categoryId) {
        return null;
    }

    @Override
    public Result addCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public Result updateCategory(Long categoryId, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public Result deleteCategory(Long categoryId) {
        return null;
    }
}
