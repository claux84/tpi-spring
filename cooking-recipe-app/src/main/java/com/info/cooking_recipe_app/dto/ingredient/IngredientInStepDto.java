package com.info.cooking_recipe_app.dto.ingredient;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentación de ingredientes en un paso"
)
public record IngredientInStepDto(@Schema(description = "Id del ingrediente") Long id,
                                  @Schema(description = "Nombre del ingrediente")String name) {

}
