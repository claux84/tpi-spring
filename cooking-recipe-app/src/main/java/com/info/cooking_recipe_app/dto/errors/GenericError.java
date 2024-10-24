package com.info.cooking_recipe_app.dto.errors;

import java.util.List;
import java.util.Map;

public record GenericError(String message,
                           List<Map<String, String>> errors) {

}
