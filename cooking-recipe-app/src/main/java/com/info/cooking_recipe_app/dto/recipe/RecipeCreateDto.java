package com.info.cooking_recipe_app.dto.recipe;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;
import com.info.cooking_recipe_app.dto.step.StepUpdateDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(
        name = "DTO creación de Receta"
)
public record RecipeCreateDto(
                              @Schema(description = "Nombre de la receta", example = "Pizza calabresa")
                              @NotNull(message = "El nombre de la receta no puede ser nulo")
                              @NotBlank(message = "El nombre de la receta no puede estar en blancoo")
                              String name,
                              @Schema(description = "Descripcion de la receta", example = "Autentica pizza calabresa")
                              @Size(max = 500, message = "La descripción de la receta no puede ser mayor a 500 caracteres")
                              String description,
                              @Schema(description = "Dificultad de la receta", example = "MEDIA")
                              @NotNull(message = "La dificultad no puede ser nula")
                              DifficultyEnum difficulty,
                              @Schema(description = "Cantidad de porciones", example = "5")
                              @Positive(message = "La cantidad de porciones debe ser positiva")
                              Long servings,
                              @Schema(description = "Id de la categoria de la receta", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                              @NotNull(message = "La categoria no puede ser nula")
                              UUID category,
                              @Schema(description = "Lista de pasos de la receta", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                              List<StepUpdateDto> stepsList) {
} 
