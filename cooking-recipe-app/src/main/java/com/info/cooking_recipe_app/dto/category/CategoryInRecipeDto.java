package com.info.cooking_recipe_app.dto.category;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentación de la categoria en una receta"
)
public record CategoryInRecipeDto(@Schema(description = "Id de la categoria") UUID id,
                                  @Schema(description = "Lista de recetas de la categoria")String name) {

}
