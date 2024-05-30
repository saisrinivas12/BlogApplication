package com.example.BlogApplication.service;

import com.example.BlogApplication.Repository.CategoryRepository;
import com.example.BlogApplication.entities.Category;
import com.example.BlogApplication.exception.ResourceNotFoundException;
import com.example.BlogApplication.payload.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       Category cat =  this.categoryRepository.save(this.categoyDtoToCategory(categoryDto));
       return this.categoryToCategoryDto(cat);
    }

    @Override
    public CategoryDto getCategoryById(int id) throws Exception {
        Category cat = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Requested categoryId not found on the server"));

        return this.categoryToCategoryDto(cat);
    }

    @Override
    public CategoryDto updateCategoryById(CategoryDto categoryDto, int id)  throws Exception{
        Category cat = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("requested id not found on the server to update"));
        Category category = this.categoyDtoToCategory(categoryDto);
        category.setCategoryId(id);
        return this.categoryToCategoryDto(this.categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map(category -> this.categoryToCategoryDto(category)).collect(Collectors.toList());
    }

    @Override
    public String deleteCategoryById(int id) {
        this.categoryRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public CategoryDto categoryToCategoryDto(Category category){
        return this.modelMapper.map(category,CategoryDto.class);
    }
    public Category categoyDtoToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto,Category.class);
    }
}
