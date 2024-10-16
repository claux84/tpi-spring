package com.info.cooking_recipe_app.dto.step;

import java.util.HashMap;

import java.util.UUID;

import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeInCategoryDto;

public record StepDto(UUID id,
                      Long estimatedTime,
                      Boolean optional,
                      String description,
                      RecipeInCategoryDto recipe,
                      HashMap<IngredientDto,Long> ingredientsQuantity) {

}
