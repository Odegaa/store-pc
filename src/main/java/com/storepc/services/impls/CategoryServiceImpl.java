package com.storepc.services.impls;

import com.storepc.models.Category;
import com.storepc.payloads.CategoryDto;
import com.storepc.repositories.CategoryRepository;
import com.storepc.services.CategoryService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Result addCategory(CategoryDto categoryDto) {
        boolean byCategoryName = categoryRepository.existsByCategoryName(categoryDto.getCategoryName());
        if (byCategoryName) {
            return new Result("This category already exist!", false, HttpStatus.CONFLICT);
        }
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            category.setParentCategory(optionalCategory.get());
            categoryRepository.save(category);
            return new Result("Category saved!", true, HttpStatus.CREATED);
        }
        category.setParentCategory(null);
        categoryRepository.save(category);
        return new Result("Category saved!", true, HttpStatus.CREATED);
    }

    @Override
    public Result updateCategory(Long categoryId, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(categoryDto.getCategoryName());
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getCategoryId());
            if (categoryOptional.isPresent()) {
                category.setParentCategory(categoryOptional.get());
                categoryRepository.save(category);
                return new Result("Category updated!", true, HttpStatus.ACCEPTED);
            }
            category.setParentCategory(null);
            categoryRepository.save(category);
            return new Result("Category updated!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Category not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return new Result("Category deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Category not found!", false, HttpStatus.ACCEPTED);
    }
}
