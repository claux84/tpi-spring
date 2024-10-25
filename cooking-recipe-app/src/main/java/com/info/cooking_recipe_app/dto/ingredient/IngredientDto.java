package com.info.cooking_recipe_app.dto.ingredient;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentacion de ingrediente"
)
public record IngredientDto(
                            @Schema(description = "Id del ingrediente") Long id,
                            @Schema(description = "Nombre del ingrediente")String name,
                            @Schema(description = "Descripción del ingrediente")String description) {
}
