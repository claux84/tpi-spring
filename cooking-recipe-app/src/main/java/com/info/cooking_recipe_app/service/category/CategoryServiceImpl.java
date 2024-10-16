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
    public Category findCategoryById(UUID idCategory) {
        return categoryRepository.findById(idCategory).orElseThrow(NoSuchElementException::new);
    }


    @Override
    public List<CategoryDto> getAllCategories() {

        return categoryRepository.findAll().stream()
                                    .map(category -> categoryMapper.categoryToCategoryDto(category))
                                    .toList();
    }

    @Override
    public CategoryDto getCategoryById(UUID idCategory) {
        Category category = findCategoryById(idCategory);
        CategoryDto  categoryDto = categoryMapper.categoryToCategoryDto(category );
        

        return categoryDto;
    }

    @Override
    public CategoryDto createCategory(CategoryCreateDto categoryCreateDto) {
        Category newCategory = categoryMapper.categoryCreateDtoToCategory(categoryCreateDto);

        return categoryMapper.categoryToCategoryDto(categoryRepository.save(newCategory));
    }

    @Override
    public boolean updateCategoryById(UUID idCategory,  CategoryCreateDto categoryUpdate) {

        if (categoryRepository.existsById(idCategory)) {
            Category categoryFromDb = findCategoryById(idCategory);
            categoryRepository.save(categoryMapper.categoryCreateDtoUpdateCategory(categoryUpdate, categoryFromDb)) ;
            return true;
            
        } else {
            return false;
        }
   
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
