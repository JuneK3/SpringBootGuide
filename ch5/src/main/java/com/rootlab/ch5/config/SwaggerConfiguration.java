package com.rootlab.ch5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 기본설정: https://bcp0109.tistory.com/326
// 상세설정: https://jeonyoungho.github.io/posts/Open-API-3.0-Swagger-v3-%EC%83%81%EC%84%B8%EC%84%A4%EC%A0%95/
// 접속시 swagger api 문서 확인가능: http://localhost:8081/swagger-ui/index.html

// useDefaultResponseMessages: false 로 설정하면 Swagger에서 제공해주는 기본 응답 코드(200, 401, 403, 404)를 노출하지 않음

@Configuration
@EnableWebMvc
public class SwaggerConfiguration {

	// ver2
	// @EnableSwagger2
	// return new Docket(DocumentationType.SWAGGER_2)
	// @ApiOperation
	// @ApiParameter

	// ver3
	// @EnableWebMvc
	// return new Docket(DocumentationType.OAS_30)
	// @Operation
	// @Parameter

	// Spring Security와 같이 사용시 설정이 달라지기도 함
	// @EnableSwagger2도 같이 사용해야 하는 경우도 있음

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rootlab.ch5.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot Open API Test with Swagger")
				.description("설명 부분")
				.version("1.0.0")
				.build();
	}

}
