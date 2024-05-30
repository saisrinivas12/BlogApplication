package com.example.BlogApplication.Controllers;

import com.example.BlogApplication.payload.CategoryDto;
import com.example.BlogApplication.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/create")
    public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto dto = service.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/get")
    public ResponseEntity<?>getAllCategories(){
        List<CategoryDto> categories = service.getAllCategories();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categories);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<?>getCategoryById(@PathVariable(name="categoryId")int categoryId) throws Exception{
        CategoryDto dto = service.getCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<?>updateCategoryById(@PathVariable(name="categoryId")int categoryId,@Valid @RequestBody CategoryDto categoryDto) throws Exception{
        CategoryDto dto = service.updateCategoryById(categoryDto,categoryId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteCategoryById(@PathVariable(name="id")int id){
        String msg = service.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }

}
