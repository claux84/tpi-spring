package com.info.cooking_recipe_app.dto.step;

import java.util.Map;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(
        name = "DTO creación de Paso"
)
public record StepCreateDto(
                            @Schema(description = "Tiempo estimado para realizar el paso", example = "5")
                            @NotNull(message = "El tiempo estimado no puede ser nulo")
                            @Positive(message = "El tiempo estimado debe ser positivo")
                            Long estimatedTime,
                            @Schema(description = "Indica si el paso es opcional o no", example = "true")
                            @NotNull(message = "La opcionalidad no puede ser nula")
                            Boolean optional,
                            @Schema(description = "Descripción de el paso", example = "Mezclar el agua con la harina")
                            @Size(max = 500, message = "La descripción del paso no puede ser mayor a 500 caracteres")
                            String description,
                            @Schema(description = "Id de la receta a la que pertenece el paso", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                            @NotNull(message = "La receta a la que pertenece el paso no puede ser nula")
                            UUID recipeUuid,
                            @Schema(description = "Lista de los ids de los ingredientes y la cantidad necesaria correspondiente en gramos", example = "[1:400]")
                            Map<@Positive(message = "El id del ingrediente debe ser positivo") Long,
                                @Positive(message = "La cantidad del ingrediente debe ser positiva") Long> ingredientsQuantity) {

}
