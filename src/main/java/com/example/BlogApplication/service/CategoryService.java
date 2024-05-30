package com.example.BlogApplication.service;

import com.example.BlogApplication.entities.Category;
import com.example.BlogApplication.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(int id) throws Exception;

    CategoryDto updateCategoryById(CategoryDto categoryDto,int id) throws Exception;

    List<CategoryDto>getAllCategories();

    String deleteCategoryById(int id );
}
