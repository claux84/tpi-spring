package com.info.cooking_recipe_app.dto.recipe;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;
import com.info.cooking_recipe_app.dto.category.CategoryInRecipeDto;

import com.info.cooking_recipe_app.dto.step.StepInRecipeDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentación de Receta"
)
public record RecipeDto(
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
                        @Schema(description = "Categoria de la receta")
                        CategoryInRecipeDto category,
                        @Schema(description = "Lista de pasos de la receta")
                        List<StepInRecipeDto> stepsList,
                        @Schema(description = "Tiempo total de preparación de la receta")
                        Long totalPreparationTime
                        ) {  

}
