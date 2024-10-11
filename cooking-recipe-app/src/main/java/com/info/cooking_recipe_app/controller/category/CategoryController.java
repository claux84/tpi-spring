package com.info.cooking_recipe_app.controller.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.category.CategoryCreateDto;
import com.info.cooking_recipe_app.dto.category.CategoryDto;
import com.info.cooking_recipe_app.service.category.CategoryService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;





@RequestMapping("/api/v1/category")
@AllArgsConstructor
@RestController
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories(){

        return categoryService.getAllCategories();

    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryCreateDto categoryCreateDto){
        CategoryDto categoryDto = categoryService.createCategory(categoryCreateDto);
        return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(categoryDto);
    }



   
}
