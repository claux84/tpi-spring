package com.info.cooking_recipe_app.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.info.cooking_recipe_app.domain.Category;
import com.info.cooking_recipe_app.dto.category.CategoryCreateDto;
import com.info.cooking_recipe_app.dto.category.CategoryDto;
import com.info.cooking_recipe_app.mappers.category.CategoryMapper;
import com.info.cooking_recipe_app.repository.category.CategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAllCategories() {

        
        return categoryRepository.findAll().stream()
                                    .map(category -> categoryMapper.CategoryToCategoryDto(category))
                                    .toList();
    }

    @Override
    public CategoryDto createCategory(CategoryCreateDto categoryCreateDto) {
        Category newCategory = categoryMapper.CategoryCreateDtoToCategory(categoryCreateDto);

        return categoryMapper.CategoryToCategoryDto(categoryRepository.save(newCategory));
    }

}
