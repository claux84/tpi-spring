package com.info.cooking_recipe_app.mappers.category;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.info.cooking_recipe_app.domain.Category;
import com.info.cooking_recipe_app.dto.category.CategoryCreateDto;
import com.info.cooking_recipe_app.dto.category.CategoryDto;
import com.info.cooking_recipe_app.dto.category.CategoryInRecipeDto;

import com.info.cooking_recipe_app.mappers.recipe.RecipeMapper;


@Mapper(uses = RecipeMapper.class)
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto( Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recipesList", ignore = true)
    Category categoryCreateDtoToCategory(CategoryCreateDto categoryCreateDto);
    
    CategoryInRecipeDto categoryToCategoryInRecipeDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
    Category categoryCreateDtoUpdateCategory(CategoryCreateDto categoryUpdate, @MappingTarget Category categoryFromDb);
    
}
