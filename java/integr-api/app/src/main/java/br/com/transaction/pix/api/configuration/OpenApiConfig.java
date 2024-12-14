package br.com.transaction.pix.api.configuration;

import java.util.function.Function;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Primary
@Configuration
@OpenAPIDefinition(
        security = @SecurityRequirement(name = "bearer-jwt")
)
@SecurityScheme(
        name = "bearer-jwt",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class OpenApiConfig {

    private final static Function<String, ApiResponse> fnApiResponse = (info) -> new ApiResponse().description(info)
            .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new io.swagger.v3.oas.models.media.MediaType().schema(new Schema())));

    @Bean
    public GroupedOpenApi cstUsuariosAPI() {
        return GroupedOpenApi.builder()
                .group("account-payment-api")
                .pathsToMatch("/v1/account-payment/**")
                .packagesToScan("br.com.transaction.pix.api.infraestructure.controller")
                .addOpenApiCustomizer(this.openApiCustomiser())
                .build();
    }

    @Bean
    public OpenAPI openApiBean() {
        return new OpenAPI()
                .info(new Info().title(System.getenv("titleApi") != null ? System.getenv("titleApi") : "Arsel API")
                        .description(System.getenv("descriptionApi") != null ? System.getenv("descriptionApi") : "Api Restfull - Contas a Pagar")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Arsel Solucoes e Tecnologia, solucoesarsel@gmail.com")
                        .url("https://arselsolucoes.com.br/"));
    }

    @Bean
    public OpenApiCustomizer openApiCustomiser() {
        return (openApi) -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
            operation.description("All paths are protected. To access them, it is necessary to enter the Authorization access token in the request header, which is retrieved through login.");
            buildResponses(operation);
        }));
    }

    private ApiResponses buildResponses(Operation operation) {
        ApiResponses apiResponses = operation.getResponses();
        apiResponses.addApiResponse("200", fnApiResponse.apply("OK"));
        apiResponses.addApiResponse("201", fnApiResponse.apply("Created"));
        apiResponses.addApiResponse("202", fnApiResponse.apply("Accepted"));
        apiResponses.addApiResponse("204", fnApiResponse.apply("No Content"));
        apiResponses.addApiResponse("301", fnApiResponse.apply("Moved Permanently"));
        apiResponses.addApiResponse("400", fnApiResponse.apply("Bad Request"));
        apiResponses.addApiResponse("401", fnApiResponse.apply("Unauthorized access"));
        apiResponses.addApiResponse("403", fnApiResponse.apply("Forbidden"));
        apiResponses.addApiResponse("404", fnApiResponse.apply("Not found"));
        apiResponses.addApiResponse("409", fnApiResponse.apply("Request problems"));
        apiResponses.addApiResponse("500", fnApiResponse.apply("Internal server error"));

        return apiResponses;
    }
}

