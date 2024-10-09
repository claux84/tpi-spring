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
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable =  false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(length = 5000)
    private String description;

    //@ManyToOne
    //@JoinColumn(name = "ingredients-amount")
    //private Step step;

}
