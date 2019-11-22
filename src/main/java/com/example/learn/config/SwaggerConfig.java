package com.example.learn.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.learn"))
				.paths(PathSelectors.ant("/api/**")).build();
	}
	//http://localhost:8080/swagger-ui.html
	//http://localhost:8080/v2/api-docs
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("This is the title").description("This is description part").version("2.0")
				.contact(new Contact("Ipsita", "https://abc.com", "ipsita.ghosal@indusnet.co.in"))
				.license("License 1.0")
				.licenseUrl("https://abc.com/license")
				.build();
	}
}
