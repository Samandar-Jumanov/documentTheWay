package com.dtw.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Document the Way API ",
                contact = @Contact(
                        name = "Samandar Jumanov",
                        url = "https://www.linkedin.com/in/samandar-jumanov-510399286/",
                        email = "jumanovsamandar005@gmail.com"
                ),
                description = "Document the Way API DOCS",
                version = "1",
                license = @License(
                        name = "MIT Licence"
                )
         ),
        security = @SecurityRequirement(
                name = "bearer"
        )

)

@SecurityScheme(
        name = "Bearer auth",
        description = "JWT token that expires in every 3 minutes",
        scheme = "bearer",
        type =  SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
