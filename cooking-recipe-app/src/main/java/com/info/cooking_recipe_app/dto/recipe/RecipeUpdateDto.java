package com.info.cooking_recipe_app.dto.recipe;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(
        name = "DTO actualización de Receta"
)
public record RecipeUpdateDto( 
                              @Schema(description = "Nombre de la receta", example = "Pizza calabresa")
                              String name,
                              @Schema(description = "Descripcion de la receta", example = "Autentica pizza calabresa")
                              @Size(max = 500, message = "La descripción de la receta no puede ser mayor a 500 caracteres")
                              String description,
                              @Schema(description = "Dificultad de la receta", example = "MEDIA")
                              DifficultyEnum difficulty,
                              @Schema(description = "Cantidad de porciones", example = "5")
                              @Positive(message = "La cantidad de porciones debe ser positiva")
                              Long servings) {

}
