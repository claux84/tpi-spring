package com.info.cooking_recipe_app.dto.category;

import java.util.List;
import java.util.UUID;


import com.info.cooking_recipe_app.dto.recipe.RecipeInCategoryDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentación de la categoria"
)

public record CategoryDto(
                          @Schema(description = "Id de la categoria")
                          UUID id,
                          @Schema(description = "Nombre de la categoria")
                          String name,
                          @Schema(description = "Lista de recetas de la categoria")
                          List<RecipeInCategoryDto> recipesList

) {

}
