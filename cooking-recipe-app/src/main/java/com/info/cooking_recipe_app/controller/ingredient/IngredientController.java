package com.info.cooking_recipe_app.controller.ingredient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.ingredient.IngredientCreateDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;
import com.info.cooking_recipe_app.service.ingredient.IngredientService;
import com.info.cooking_recipe_app.validator.groups.ValidatorGroups.Create;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;

import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RequestMapping("/api/v1/ingredient")
@AllArgsConstructor
@RestController
public class IngredientController {

   
    private IngredientService ingredientService;

    @GetMapping
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{idIngredient}")
    public IngredientDto getIngredientById(@PathVariable("idIngredient") Long idIngredient){
        return ingredientService.getIngredientById(idIngredient);
    }

    @PostMapping
    public ResponseEntity<?> createIngredient(@Validated({Create.class, Default.class}) @RequestBody IngredientCreateDto ingredientCreateDto){
        IngredientDto ingredientDto = ingredientService.createIngredient(ingredientCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                              .body(ingredientDto);
    }

    @PutMapping("/{idIngredient}")
    public ResponseEntity<?> updateIngredientById(@PathVariable("idIngredient") Long idIngredient, @Valid @RequestBody IngredientCreateDto ingredientUpdate) {
        boolean isIngredientUpdated = ingredientService.updateIngredientById(idIngredient, ingredientUpdate);
        if (isIngredientUpdated) {
            IngredientDto ingredientUpdated = getIngredientById(idIngredient);
            return ResponseEntity.ok().body(ingredientUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    

    @DeleteMapping("/{idIngredient}")
    public ResponseEntity<?> deleteIngredientById(@PathVariable("idIngredient") Long idIngredient){
        boolean isIngredientDeleted = ingredientService.deleteIngredientById(idIngredient);
        if (isIngredientDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    




    



}
