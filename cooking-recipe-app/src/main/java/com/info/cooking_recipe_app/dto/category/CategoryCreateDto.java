package com.info.cooking_recipe_app.dto.category;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(
        name = "DTO creación de la categoria"
)

public record CategoryCreateDto(
                                @Schema(description = "Nombre de la categoria", example = "Panaderia")
                                @NotNull(message = "El nombre de la categoria no puede ser nulo")
                                @NotBlank(message = "El nombre de la categoria no puede estar en blanco")
                                String name) {

}
