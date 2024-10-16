package com.info.cooking_recipe_app.mappers.ingredient;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.info.cooking_recipe_app.domain.Ingredient;

import com.info.cooking_recipe_app.dto.ingredient.IngredientCreateDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientInStepDto;

@Mapper
public interface IngredientMapper {

    IngredientDto ingredientToIngredientDto(Ingredient ingredient);

    @Mapping(target = "id", ignore = true)
    Ingredient ingredientCreateDtoToIngredient(IngredientCreateDto ingredientCreateDto);

    IngredientInStepDto ingredientToIngredientInStepDto(Ingredient ingredient);

    @BeanMapping(nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
    Ingredient ingredientCreateDtoToUpdateCategory( IngredientCreateDto categoryUpdate, @MappingTarget Ingredient ingredientFromDb);

}
