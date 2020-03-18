package com.insulinbond.swaggerConfig;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

/**
 * Swagger 2 Configuration to see API List
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * This is the setup for Swagger, go tot this link to view: http://localhost:8099/swagger-ui.html
     * @return
     * @throws IOException
     * @throws XmlPullParserException
     */
    @Bean
    public Docket swaggerConfiguration() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.insulinbond")).build()
                .apiInfo(apiDetails());
    }

    /**
     * Swagger Info Detail
     * @return
     */
    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Project Insulin Bond API",
                "Sample API For ProjectIB.com",
                "1.0",
                "Free To use",
                new springfox.documentation.service.Contact("Anish Niroula, Mohit Paudyal",
                        "https://ProjectIB.com", "ProjectIB@gmail.com"),
                "API License",
                "https://ProjectIB.com",
                Collections.emptyList());
    }

}
