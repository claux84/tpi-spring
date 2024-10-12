package com.info.cooking_recipe_app.service.category;

import java.util.List;
import java.util.UUID;
import java.util.NoSuchElementException;

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
                                    .map(category -> categoryMapper.categoryToCategoryDto(category))
                                    .toList();
    }

    @Override
    public CategoryDto getCategoryById(UUID idCCategory) {
        Category category = categoryRepository.findById(idCCategory).orElseThrow(NoSuchElementException::new);

        return categoryMapper.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto createCategory(CategoryCreateDto categoryCreateDto) {
        Category newCategory = categoryMapper.categoryCreateDtoToCategory(categoryCreateDto);

        return categoryMapper.categoryToCategoryDto(categoryRepository.save(newCategory));
    }

    @Override
    public boolean deleteCategoryById(UUID idCategory) {
        if (categoryRepository.existsById(idCategory)) {
            categoryRepository.deleteById(idCategory);
            return true;
        } else {
            return false;
        }
    }

}
