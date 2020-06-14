package br.com.alura.microservice.transportador.docs;
//http://localhost:8080/swagger-ui.html#/compra-controller
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig{

	public SwaggerConfig() {
		super("br.com.alura.microservice.transportador.controller");
		
	}

}
