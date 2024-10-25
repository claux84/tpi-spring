package com.info.cooking_recipe_app.dto.recipe;


import java.util.UUID;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentación de receta en categoria"
)
public record RecipeInCategoryDto(
                                  @Schema(description = "Id de la receta")  
                                  UUID id,
                                  @Schema(description = "Nombre de la receta")
                                  String name,
                                  @Schema(description = "Descripción de la receta")
                                  String description,
                                  @Schema(description = "Dificultad de la receta")
                                  DifficultyEnum difficulty,
                                  @Schema(description = "Cantidad de porciones")
                                  Long servings,
                                  @Schema(description = "Tiempo total de preparación de la receta")
                                  Long totalPreparationTime) {

}
