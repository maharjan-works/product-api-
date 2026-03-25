package com.maharjanworks.product_api;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Product Rest API documentation",
				description= "Product Rest API",
				version = "v1",
				contact = @Contact(
						name = "maharjan works",
						email="maharjan.works@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Sharepoint URL Product Service API",
				url ="maharjanworks.com"
		)
)
@SpringBootApplication
public class ProductApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApiApplication.class, args);
	}

}
