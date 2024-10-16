package com.info.cooking_recipe_app.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable =  false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 5000)
    private String description;


}
