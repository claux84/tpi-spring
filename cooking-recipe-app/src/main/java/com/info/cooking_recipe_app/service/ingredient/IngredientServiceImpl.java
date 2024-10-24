package com.info.cooking_recipe_app.service.ingredient;

import java.util.List;



import org.springframework.stereotype.Service;

import com.info.cooking_recipe_app.domain.Ingredient;
import com.info.cooking_recipe_app.dto.ingredient.IngredientCreateDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;
import com.info.cooking_recipe_app.exceptions.ResourceNotFoundException;
import com.info.cooking_recipe_app.mappers.ingredient.IngredientMapper;
import com.info.cooking_recipe_app.repository.ingredient.IngredientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    private IngredientMapper ingredientMapper;

    @Override
    public Ingredient findIngredientById(Long idIngredient) {
        return ingredientRepository.findById(idIngredient)
                                   .orElseThrow(() -> new ResourceNotFoundException("El ingrediente con el id: "+ idIngredient + " no fue encontrado"));
    }



    @Override
    public List<IngredientDto> getAllIngredients() {
        
        return ingredientRepository.findAll().stream()
                                    .map(ingredient -> ingredientMapper.ingredientToIngredientDto(ingredient))
                                    .toList();
    }

    @Override
    public IngredientDto getIngredientById(Long idIngredient) {
         return ingredientMapper.ingredientToIngredientDto(findIngredientById(idIngredient));
    }

    @Override
    public IngredientDto createIngredient(IngredientCreateDto ingredientCreateDto) {
        Ingredient newIngredient = ingredientMapper.ingredientCreateDtoToIngredient(ingredientCreateDto);
        return ingredientMapper.ingredientToIngredientDto(ingredientRepository.save(newIngredient));
    }

    @Override
    public boolean updateIngredientById(Long idIngredient, IngredientCreateDto ingredientUpdate) {
        if (ingredientRepository.existsById(idIngredient)) {
            Ingredient ingredientFromDb = findIngredientById(idIngredient);
            ingredientRepository.save(ingredientMapper.ingredientCreateDtoToUpdateCategory(ingredientUpdate, ingredientFromDb));
            return true;
        } else {
            return false;
        }
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
