package com.info.cooking_recipe_app.service.category;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.domain.Category;
import com.info.cooking_recipe_app.dto.category.CategoryCreateDto;
import com.info.cooking_recipe_app.dto.category.CategoryDto;

public interface CategoryService {

    Category findCategoryById( UUID idCategory);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById( UUID idCategory);

    CategoryDto createCategory( CategoryCreateDto categoryCreateDto);

    boolean updateCategoryById( UUID idCategory, CategoryCreateDto categoryUpdate);

    boolean deleteCategoryById( UUID idCategory);

}
