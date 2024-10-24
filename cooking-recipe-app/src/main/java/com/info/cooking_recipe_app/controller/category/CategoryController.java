package com.info.cooking_recipe_app.controller.category;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.category.CategoryCreateDto;
import com.info.cooking_recipe_app.dto.category.CategoryDto;
import com.info.cooking_recipe_app.service.category.CategoryService;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;






@RequestMapping("/api/v1/category")
@AllArgsConstructor
@RestController
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories(){

        return categoryService.getAllCategories();

    }

    @GetMapping("/{idCategory}")
    public CategoryDto getCategoryById (@PathVariable("idCategory") UUID idCategory){
        return categoryService.getCategoryById(idCategory);
    }

    @PostMapping
    public ResponseEntity<?> createCategory( @Valid @RequestBody CategoryCreateDto categoryCreateDto){
        CategoryDto categoryDto = categoryService.createCategory(categoryCreateDto);
        return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(categoryDto);
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<?> putMethodName(@PathVariable("idCategory") UUID idCategory, @Valid @RequestBody CategoryCreateDto categoryUpdate) {
        boolean isCategoryUpdated = categoryService.updateCategoryById(idCategory, categoryUpdate);
        if (isCategoryUpdated) {
            CategoryDto categoryUpdated = getCategoryById(idCategory);
            return ResponseEntity.ok().body(categoryUpdated);
        } else {
            return ResponseEntity.notFound().build();
            
        }
        
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("idCategory") UUID idCategory){
        boolean isCategoryDeleted = categoryService.deleteCategoryById(idCategory);
        if (isCategoryDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



   
}
