package com.info.cooking_recipe_app.service.ingredient;

import java.util.List;

import org.springframework.stereotype.Service;

import com.info.cooking_recipe_app.domain.Ingredient;
import com.info.cooking_recipe_app.dto.ingredient.IngredientCreateDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;
import com.info.cooking_recipe_app.mappers.ingredient.IngredientMapper;
import com.info.cooking_recipe_app.repository.ingredient.IngredientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    private IngredientMapper ingredientMapper;

    @Override
    public List<IngredientDto> getAllIngredients() {
        
        return ingredientRepository.findAll().stream()
                                    .map(ingredient -> ingredientMapper.ingredientToIngredientDto(ingredient))
                                    .toList();
    }

    @Override
    public IngredientDto createIngredient(IngredientCreateDto ingredientCreateDto) {
        Ingredient newIngredient = ingredientMapper.ingredientCreateDtoToIngredient(ingredientCreateDto);
        return ingredientMapper.ingredientToIngredientDto(ingredientRepository.save(newIngredient));
    }

}
