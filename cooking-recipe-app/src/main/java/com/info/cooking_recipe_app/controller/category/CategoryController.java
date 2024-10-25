package com.info.cooking_recipe_app.controller.category;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.category.CategoryCreateDto;
import com.info.cooking_recipe_app.dto.category.CategoryDto;
import com.info.cooking_recipe_app.dto.errors.ErrorDto;
import com.info.cooking_recipe_app.service.category.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;






@RequestMapping("/api/v1/category")
@AllArgsConstructor
@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Operation(
            summary = "API REST para mostrar todas las categorias",
            description = "API REST para mostrar todas las categorias"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Las categorias estan presentadas",
                    content = @Content(
                        schema = @Schema(implementation = CategoryDto.class)
                        )
            )
    })
    @GetMapping
    public List<CategoryDto> getAllCategories(){

        return categoryService.getAllCategories();

    }

    @Operation(
            summary = "API REST para mostrar una categoria",
            description = "API REST para mostrar una categoria y sus recetas dado el id de la categoria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "La categoria ha sido encontrada",
                    content = @Content(
                        schema = @Schema(implementation = CategoryDto.class)
                        )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro la categoria",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @GetMapping("/{idCategory}")
    public CategoryDto getCategoryById (@PathVariable("idCategory") UUID idCategory){
        return categoryService.getCategoryById(idCategory);
    }

    @Operation(
            summary = "API REST para crear una categoria",
            description = "API REST que permite crear una categoria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "La categoria fue creada",
                    content = @Content(
                        schema = @Schema(implementation = CategoryDto.class)
                        )
            ),
            
    })
    @PostMapping
    public ResponseEntity<?> createCategory( @Valid @RequestBody CategoryCreateDto categoryCreateDto){
        CategoryDto categoryDto = categoryService.createCategory(categoryCreateDto);
        return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(categoryDto);
    }

    @Operation(
            summary = "API REST para actualizar una categoria",
            description = "API REST que permite actualizar una categoria dada su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "La categoria fue actualizada",
                    content = @Content(
                        schema = @Schema(implementation = CategoryDto.class)
                        )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro la categoria",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @PutMapping("/{idCategory}")
    public ResponseEntity<?> putMethodName(@PathVariable("idCategory") UUID idCategory, @Valid @RequestBody CategoryCreateDto categoryUpdate) {
        boolean isCategoryUpdated = categoryService.updateCategoryById(idCategory, categoryUpdate);
        if (isCategoryUpdated) {
            CategoryDto categoryUpdated = getCategoryById(idCategory);
            return ResponseEntity.ok().body(categoryUpdated);
        } else {
            return ResponseEntity.notFound().build();
            
        }
        
    }

    @Operation(
            summary = "API REST para eliminar una categoria",
            description = "API REST que permite eliminar una categoria dado su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "La categoria fue eliminada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro la categoria",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("idCategory") UUID idCategory){
        boolean isCategoryDeleted = categoryService.deleteCategoryById(idCategory);
        if (isCategoryDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



   
}
