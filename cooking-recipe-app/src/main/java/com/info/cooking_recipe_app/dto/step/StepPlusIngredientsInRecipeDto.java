package com.info.cooking_recipe_app.dto.step;

import java.util.HashMap;
import java.util.UUID;

import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentación de los ingredientes de un paso"
)
public record StepPlusIngredientsInRecipeDto(@Schema(description = "Id del paso") 
                                             UUID id,
                                             @Schema(description = "Lista de ingredientes y sus cantidades en gramos")
                                             HashMap<IngredientDto,Long> ingredientsQuantity) {

}
