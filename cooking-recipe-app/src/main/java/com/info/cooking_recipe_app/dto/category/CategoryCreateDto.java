package com.info.cooking_recipe_app.dto.category;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryCreateDto(
                                @NotNull(message = "El nombre de la categoria no puede ser nulo")
                                @NotBlank(message = "El nombre de la categoria no puede estar en blanco")
                                String name) {

}
