package com.info.cooking_recipe_app.dto.step;

import java.util.Map;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record StepCreateDto(
                            @NotNull(message = "El tiempo estimado no puede ser nulo")
                            @Positive(message = "El tiempo estimado debe ser positivo")
                            Long estimatedTime,
                            @NotNull(message = "La opcionalidad no puede ser nula")
                            Boolean optional,
                            @Size(max = 500, message = "La descripción del paso no puede ser mayor a 500 caracteres")
                            String description,
                            @NotNull(message = "La receta a la que pertenece el paso no puede ser nula")
                            UUID recipeUuid,
                            Map<@Positive(message = "El id del ingrediente debe ser positivo") Long,
                                @Positive(message = "La cantidad del ingrediente debe ser positiva") Long> ingredientsQuantity) {

}
