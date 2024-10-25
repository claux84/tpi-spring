package com.info.cooking_recipe_app.dto.recipe;


import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.dto.step.StepPlusIngredientsInRecipeDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO ingredientes de una receta"
)
public record RecipeIngredientDto(@Schema(description = "Id de la receta")  UUID id,
                                  @Schema(description = "Nombre de la receta") String name,
                                  @Schema(description = "Lista de pasos de la receta") List<StepPlusIngredientsInRecipeDto> stepsList
                                  ) {

}
