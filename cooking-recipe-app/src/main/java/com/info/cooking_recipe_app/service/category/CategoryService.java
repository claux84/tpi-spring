package com.info.cooking_recipe_app.service.category;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.dto.category.CategoryCreateDto;
import com.info.cooking_recipe_app.dto.category.CategoryDto;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById( UUID idCCategory);

    CategoryDto createCategory( CategoryCreateDto categoryCreateDto);

    boolean deleteCategoryById( UUID idCategory);

}
