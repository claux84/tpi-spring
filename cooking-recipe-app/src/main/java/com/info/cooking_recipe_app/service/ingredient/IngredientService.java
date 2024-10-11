package com.info.cooking_recipe_app.service.ingredient;

import java.util.List;

import com.info.cooking_recipe_app.dto.ingredient.IngredientCreateDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;

public interface IngredientService {

    List<IngredientDto> getAllIngredients();

    IngredientDto createIngredient(IngredientCreateDto ingredientCreateDto);

}
