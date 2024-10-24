package com.info.cooking_recipe_app.mappers.step;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.info.cooking_recipe_app.domain.Step;
import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;
import com.info.cooking_recipe_app.dto.step.StepInRecipeDto;
import com.info.cooking_recipe_app.dto.step.StepPlusIngredientsInRecipeDto;
import com.info.cooking_recipe_app.dto.step.StepUpdateDto;
import com.info.cooking_recipe_app.mappers.recipe.RecipeMapper;

@Mapper(uses = RecipeMapper.class)
public interface StepMapper {

    StepDto stepToStepDto(Step step);

    @Mapping(source = "ingredientsQuantity", target = "ingredientsQuantity")
    @Mapping(target = "recipe", ignore = true)
    Step stepDtoToStep(StepDto stepDto);

    @Mapping(target = "ingredientsQuantity", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recipe", ignore = true)
    Step stepCreateDtoToStep(StepCreateDto stepCreateDto);

    
    StepInRecipeDto stepToStepInRecipeDto(Step step);

    @Mapping(target = "ingredientsQuantity", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
    Step stepUdateDtoToStep(StepUpdateDto stepUpdateDto, @MappingTarget Step stepFromDb);

    StepPlusIngredientsInRecipeDto StepToStepPlusIngredientInRecipeDto( Step step);

    @Mapping(target = "ingredientsQuantity", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recipe", ignore = true)
    Step stepCreateInRecipeToStep(StepUpdateDto stepUpdateDto);
}
