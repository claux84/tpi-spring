package com.info.cooking_recipe_app.dto.recipe;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;
import com.info.cooking_recipe_app.dto.step.StepUpdateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RecipeCreateDto(
                              @NotNull(message = "El nombre de la receta no puede ser nulo")
                              @NotBlank(message = "El nombre de la receta no puede estar en blancoo")
                              String name,
                              @Size(max = 500, message = "La descripción de la receta no puede ser mayor a 500 caracteres")
                              String description,
                              @NotNull(message = "La dificultad no puede ser nula")
                              DifficultyEnum difficulty,
                              @Positive(message = "La cantidad de porciones debe ser positiva")
                              Long servings,
                              @NotNull(message = "La categoria no puede ser nula")
                              UUID category,
                              List<StepUpdateDto> stepsList) {
} 
