package com.info.cooking_recipe_app.service.category;

import java.util.List;

import com.info.cooking_recipe_app.dto.category.CategoryCreateDto;
import com.info.cooking_recipe_app.dto.category.CategoryDto;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto createCategory( CategoryCreateDto categoryCreateDto);

}
