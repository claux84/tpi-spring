package com.info.cooking_recipe_app.service.ingredient;

import java.util.List;
import java.util.NoSuchElementException;


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
    public IngredientDto getIngredientById(Long idIngredient) {
         Ingredient ingredient = ingredientRepository.findById(idIngredient).orElseThrow(NoSuchElementException::new);
         return ingredientMapper.ingredientToIngredientDto(ingredient);
    }

    @Override
    public IngredientDto createIngredient(IngredientCreateDto ingredientCreateDto) {
        Ingredient newIngredient = ingredientMapper.ingredientCreateDtoToIngredient(ingredientCreateDto);
        return ingredientMapper.ingredientToIngredientDto(ingredientRepository.save(newIngredient));
    }

    @Override
    public boolean deleteIngredientById(Long idIngredient) {
        if (ingredientRepository.existsById(idIngredient)) {
            ingredientRepository.deleteById(idIngredient);
            return true;
        } else {
            return false;
        }

    }

}
