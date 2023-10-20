package one.digitalinnovation.labdesignpatternsspring.core.configurations;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfiguration {
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Custumer Rest API")
                        .description("Client Address Management API")
                        .version("1.0")
                        .termsOfService("OpenSource")
                        .license(new License()
                                .name("User API os")
                                .url("http://www.apis-ipsum-lorem.opensource.com/doc")
                        )
                ).externalDocs(
                        new ExternalDocumentation()
                                .description("Github")
                                .url("https://github.com/rafael-deroncio"));
    }
}