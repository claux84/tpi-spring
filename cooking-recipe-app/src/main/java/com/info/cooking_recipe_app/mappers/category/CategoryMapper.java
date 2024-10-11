package com.info.cooking_recipe_app.mappers.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.info.cooking_recipe_app.domain.Category;
import com.info.cooking_recipe_app.dto.category.CategoryCreateDto;
import com.info.cooking_recipe_app.dto.category.CategoryDto;
import com.info.cooking_recipe_app.dto.category.CategoryInRecipeDto;

@Mapper
public interface CategoryMapper {

    
    CategoryDto CategoryToCategoryDto( Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recipesList", ignore = true)
    Category CategoryCreateDtoToCategory(CategoryCreateDto categoryCreateDto);
    
    CategoryInRecipeDto categoryToCategoryInRecipeDto(Category category);
    
}
