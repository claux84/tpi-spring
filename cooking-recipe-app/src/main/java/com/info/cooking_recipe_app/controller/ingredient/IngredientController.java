package com.info.cooking_recipe_app.controller.ingredient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.ingredient.IngredientCreateDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;
import com.info.cooking_recipe_app.service.ingredient.IngredientService;

import lombok.AllArgsConstructor;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;




@RequestMapping("/api/v1/ingredient")
@AllArgsConstructor
@RestController
public class IngredientController {

   
    private IngredientService ingredientService;

    @GetMapping
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @PostMapping
    public ResponseEntity<?> createIngredient(@RequestBody IngredientCreateDto ingredientCreateDto){
        IngredientDto ingredientDto = ingredientService.createIngredient(ingredientCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                              .body(ingredientDto);
    }


    



}
