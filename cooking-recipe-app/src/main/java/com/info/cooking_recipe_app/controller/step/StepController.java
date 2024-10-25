package com.info.cooking_recipe_app.controller.step;

import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.errors.ErrorDto;
import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;
import com.info.cooking_recipe_app.dto.step.StepUpdateDto;
import com.info.cooking_recipe_app.service.step.StepService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RequestMapping("/api/v1/step")
@AllArgsConstructor
@RestController
public class StepController {

    private StepService stepService;

    @Operation(
            summary = "API REST para mostrar todas los pasos",
            description = "API REST que permite mostrar todos los pasos"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Los pasos estan presentados",
                    content = @Content(
                        schema = @Schema(implementation = StepDto.class)
                        )
            )
    })
    @GetMapping
    public List<StepDto> getAllSteps() {
        return stepService.getAllSteps();
    }

    @Operation(
            summary = "API REST para mostrar un paso",
            description = "API REST que permite mostrar un paso dado su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "El paso ha sido encontrado",
                    content = @Content(
                        schema = @Schema(implementation = StepDto.class)
                        )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro el paso",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @GetMapping("/{idStep}")
    public StepDto getStepById(@PathVariable("idStep") UUID idStep) {

        return stepService.getStepById(idStep);
    }
    
    @Operation(
        summary = "API REST para crear un paso",
        description = "API REST que permite crear un paso"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "El paso fue creado",
                content = @Content(
                    schema = @Schema(implementation = StepDto.class)
                    )
        ),
        @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro la receta",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<?> createStep(@Valid @RequestBody StepCreateDto stepCreateDto) {
        StepDto stepDto = stepService.createStep(stepCreateDto);
        
        return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(stepDto);
    }

    @Operation(
            summary = "API REST para actualizar un paso",
            description = "API REST que permite actualizar el paso dado su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "El paso fue actualizada",
                    content = @Content(
                        schema = @Schema(implementation = StepDto.class)
                        )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro el paso",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @PutMapping("/{idStep}")
    public ResponseEntity<?> updateStepById(@PathVariable("idStep") UUID idStep, @Valid @RequestBody StepUpdateDto stepUpdate) {
        boolean isStepUpdated = stepService.updateStepById(idStep, stepUpdate);
        if (isStepUpdated) {
            StepDto stepUpdated = getStepById(idStep);
            return ResponseEntity.ok().body(stepUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }

    @Operation(
            summary = "API REST para eliminar un paso",
            description = "API REST que permite eliminar un paso dado su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "El paso fue eliminado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro el paso",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @DeleteMapping("/{idStep}")
    public ResponseEntity<?> deleteStepById(@PathVariable("idStep") UUID idStep){
        boolean isStepDeleted = stepService.deleteStepById(idStep);
        if (isStepDeleted) {
            return ResponseEntity.noContent()
                                 .build();
        } else {
            return ResponseEntity.notFound()
                                 .build();
        }
    }
    



}
