package com.info.cooking_recipe_app.mappers.recipe;


import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.info.cooking_recipe_app.domain.Recipe;
import com.info.cooking_recipe_app.dto.recipe.RecipeCreateDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeInCategoryDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeIngredientDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeUpdateDto;
import com.info.cooking_recipe_app.dto.step.StepPlusIngredientsInRecipeDto;




@Mapper
public interface RecipeMapper {

    @Mapping(target = "totalPreparationTime", expression = "java(recipe.getTotalPreparationTime())")
    RecipeDto recipeToRecipeDto(Recipe recipe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stepsList", ignore = true)
    @Mapping(target = "category", ignore = true)
    Recipe recipeCreateDtoToRecipe(RecipeCreateDto recipeCreateDto);   
    
    @Mapping(target = "totalPreparationTime", expression = "java(recipe.getTotalPreparationTime())")
    RecipeInCategoryDto recipeToRecipeInCategoryDto(Recipe recipe);

  
    @BeanMapping(nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
    Recipe toRecipe(RecipeUpdateDto recipeUpdateDto, @MappingTarget Recipe recipe);

    @Mapping(target = "stepsList", source = "stepList")
    RecipeIngredientDto RecipeToRecipeIngredientDto(Recipe recipe, List<StepPlusIngredientsInRecipeDto> stepList);
}
