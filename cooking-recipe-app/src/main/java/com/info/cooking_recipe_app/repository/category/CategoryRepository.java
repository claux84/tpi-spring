package com.info.cooking_recipe_app.repository.category;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.cooking_recipe_app.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID>{

}
