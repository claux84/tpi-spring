package com.info.cooking_recipe_app.dto.recipe;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RecipeUpdateDto( 
                              String name,
                              @Size(max = 500, message = "La descripción de la receta no puede ser mayor a 500 caracteres")
                              String description,
                              DifficultyEnum difficulty,
                              @Positive(message = "La cantidad de porciones debe ser positiva")
                              Long servings) {

}
