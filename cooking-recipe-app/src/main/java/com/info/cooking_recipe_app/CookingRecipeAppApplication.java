package com.info.cooking_recipe_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Proyecto API REST del TPI Curso Spring",
				description = "Documentación de la API REST del trabajoPractico Integrador de Spring",
				version = "v1",
				contact = @Contact(
						name = "Claudio Hugo Ramírez",
						email = "claux4891@gmail.com",
						url = "https://www.claudioramirez.com.ar"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Proyecto Integrador de Spring para el Info 2024",
				url = "https://www.claudioramirez.com.ar"
		)
)
public class CookingRecipeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookingRecipeAppApplication.class, args);
	}

}
