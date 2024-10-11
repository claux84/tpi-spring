package com.info.cooking_recipe_app.mappers.recipe;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.info.cooking_recipe_app.domain.Recipe;
import com.info.cooking_recipe_app.dto.recipe.RecipeCreateDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeInCategoryDto;

@Mapper
public interface RecipeMapper {

    
    RecipeDto RecipeToRecipeDto(Recipe recipe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stepsList", ignore = true)
    @Mapping(target = "category", ignore = true)
    Recipe RecipeCreateDtoToRecipe(RecipeCreateDto recipeCreateDto);   
    
    RecipeInCategoryDto recipeToRecipeInCategoryDto(Recipe recipe);
    
}
