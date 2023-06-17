package com.br.evandro.paymentservice.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    OpenAPI springShopOpenAPI() {
    	OpenAPI openApi = new OpenAPI()
                .info(new Info().title(System.getenv("titleApi") != null ? System.getenv("titleApi") : "Nova API")
                .description(System.getenv("descriptionApi") != null ? System.getenv("descriptionApi") : "New Application")
                .version("1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                .description("Evandro Ribeiro ER, eribeiro18@gmail.com")
                .url("https://newapp.com.br"));
        return openApi;
    }
    
    @Bean
    OpenApiCustomizer customOpenApiCustomiser() {
    	return (openApi) -> {
    		openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
    		  ApiResponses apiResponses = operation.getResponses();
    		  apiResponses.addApiResponse("400", createApiResponse("Solicitação Inválida", null));
    		  apiResponses.addApiResponse("403", createApiResponse("Sem os privilegios necessarios para acessar este recurso", null));
    		  apiResponses.addApiResponse("404", createApiResponse("Serviço não encontrado", null));
    		  apiResponses.addApiResponse("500", createApiResponse("Erro interno no servidor", null));
    		}));
    	};
    }
    
    private ApiResponse createApiResponse(String message, Schema<?> schema) {
    	  MediaType mediaType = new MediaType();
    	  mediaType.schema(schema);
    	  return new ApiResponse().description(message)
    	                .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, mediaType));
    	}
    
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
