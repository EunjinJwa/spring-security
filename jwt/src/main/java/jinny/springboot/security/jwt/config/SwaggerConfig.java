package jinny.springboot.security.jwt.config;

import com.google.common.net.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		// 모든 API에 공통 parameter 지정
		Parameter parameterBuilder = new ParameterBuilder()
				.name(HttpHeaders.AUTHORIZATION)
				.description("Access JWT Token")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build();

		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(Collections.singletonList(parameterBuilder))
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("jinny.springboot.security.jwt"))
				.paths(PathSelectors.any())
				.build();
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Security by JWT")
				.version("0.0.1")
				.description("JWT를 사용한 API 인증 API 명세서")
				.build();
	}

}
