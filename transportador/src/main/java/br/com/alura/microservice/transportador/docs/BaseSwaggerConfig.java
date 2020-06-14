package br.com.alura.microservice.transportador.docs;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class BaseSwaggerConfig {
	private final String basePackage;
	
	public BaseSwaggerConfig(String basePackage) {
		this.basePackage = basePackage;
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))
				.build()
				.apiInfo(metaData());
		
	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Microservices Api")
				.description("service Rest Api")
				.version("V1.0")
				.contact(new Contact("Rodrigo Tenorio de Boa Ventura", "https://github.com/RodrigoTenorio86", "boaventura19@yahoo.com.br"))
				.license("MIT")
				.licenseUrl("MIT.com")
				.build();
		
	}
}
