package com.info.cooking_recipe_app.dto.step;

import java.util.Map;

import com.info.cooking_recipe_app.validator.groups.ValidatorGroups.Create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(
        name = "DTO actualizacion de un Paso"
)
public record StepUpdateDto(
                            @Schema(description = "Tiempo estimado para realizar el paso", example = "5")
                            @NotNull(message = "El tiempo estimado no puede ser nulo", groups = Create.class)
                            @Positive(message = "El tiempo estimado debe ser positivo")
                            Long estimatedTime,
                            @Schema(description = "Indica si el paso es opcional o no", example = "true")
                            @NotNull(message = "La opcionalidad no puede ser nula", groups = Create.class)
                            Boolean optional,
                            @Schema(description = "Descripción de el paso", example = "Mezclar el agua con la harina")
                            @Size(max = 500, message = "La descripción del paso no puede ser mayor a 500 caracteres")
                            String description,
                            @Schema(description = "Lista de los ids de los ingredientes y la cantidad necesaria correspondiente en gramos", example = "[1:400]")
                            Map<@Positive(message = "El id del ingrediente debe ser positivo") Long,
                                @Positive(message = "La cantidad del ingrediente debe ser positiva") Long> ingredientsQuantity) {

}
