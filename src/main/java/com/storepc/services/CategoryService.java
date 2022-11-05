package com.storepc.services;

import com.storepc.models.Category;
import com.storepc.payloads.CategoryDto;
import com.storepc.templates.Result;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategory(Long categoryId);

    Result addCategory(CategoryDto categoryDto);

    Result updateCategory(Long categoryId, CategoryDto categoryDto);

    Result deleteCategory(Long categoryId);

}
