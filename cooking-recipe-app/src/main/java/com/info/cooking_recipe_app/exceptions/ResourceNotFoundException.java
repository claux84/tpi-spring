package com.info.cooking_recipe_app.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException( String message){
        super(message);
    }

}
