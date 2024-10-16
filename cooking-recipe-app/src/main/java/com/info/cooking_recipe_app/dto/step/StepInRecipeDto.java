package com.info.cooking_recipe_app.dto.step;

import java.util.HashMap;
import java.util.UUID;

import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;

public record StepInRecipeDto(UUID id,
                              Long estimatedTime,
                              Boolean optional,
                              String description,
                              HashMap<IngredientDto,Long> ingredientsQuantity) {

}
