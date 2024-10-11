package com.info.cooking_recipe_app.mappers.step;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.info.cooking_recipe_app.domain.Step;
import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;
import com.info.cooking_recipe_app.dto.step.StepInRecipeDto;

@Mapper
public interface StepMapper {

    StepDto StepToStepDto(Step step);

    @Mapping(target = "ingredientsList", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recipe", ignore = true)
    Step StepCreateDtoToStep(StepCreateDto stepCreateDto);

    StepInRecipeDto stepToStepInRecipeDto(Step step);
}
