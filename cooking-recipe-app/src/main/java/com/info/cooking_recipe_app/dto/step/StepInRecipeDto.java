package com.info.cooking_recipe_app.dto.step;

import java.util.HashMap;
import java.util.UUID;

import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentación de paso en una receta"
)
public record StepInRecipeDto(
                              @Schema(description = "Id del paso")
                              UUID id,
                              @Schema(description = "Tiempo estimado para realizar el paso en minutos")
                              Long estimatedTime,
                              @Schema(description = "Indica si el paso es opcional")
                              Boolean optional,
                              @Schema(description = "Descripción del paso")
                              String description,
                              @Schema(description = "Lista de ingredientes y sus cantidades en gramos")
                              HashMap<IngredientDto,Long> ingredientsQuantity) {

}
