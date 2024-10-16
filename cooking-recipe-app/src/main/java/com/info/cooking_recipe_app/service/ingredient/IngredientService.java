package com.info.cooking_recipe_app.service.ingredient;

import java.util.List;

import com.info.cooking_recipe_app.domain.Ingredient;
import com.info.cooking_recipe_app.dto.ingredient.IngredientCreateDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;

public interface IngredientService {

    Ingredient findIngredientById(Long idIngredient);

    List<IngredientDto> getAllIngredients();

    IngredientDto getIngredientById(Long idIngredient);

    IngredientDto createIngredient(IngredientCreateDto ingredientCreateDto);

    boolean updateIngredientById(Long idIngredient, IngredientCreateDto ingredientUpdate);

    boolean deleteIngredientById(Long idIngredient);



}
