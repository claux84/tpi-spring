package com.info.cooking_recipe_app.dto.ingredient;

import com.info.cooking_recipe_app.validator.groups.ValidatorGroups.Create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(
        name = "DTO creación de ingredientes"
)
public record IngredientCreateDto(
                                  @Schema(description = "Nombre del ingrediente", example = "agua")
                                  @NotNull(message = "El nombre del ingrediente no puede ser nulo", groups = Create.class)
                                  @NotBlank(message = "El nombre del ingrediente no puede estar en blanco", groups = Create.class)
                                  String name,
                                  @Schema(description = "Descripcion del ingrediente", example = "Agua potable")
                                  @Size(max = 500, message = "La descripción de la receta no puede ser mayor a 500 caracteres")
                                  String description) {

}
