package com.info.cooking_recipe_app.dto.ingredient;

import com.info.cooking_recipe_app.validator.groups.ValidatorGroups.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IngredientCreateDto(
                                  @NotNull(message = "El nombre del ingrediente no puede ser nulo", groups = Create.class)
                                  @NotBlank(message = "El nombre del ingrediente no puede estar en blanco", groups = Create.class)
                                  String name,
                                  @Size(max = 500, message = "La descripción de la receta no puede ser mayor a 500 caracteres")
                                  String description) {

}
