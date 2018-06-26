package com.challenge.analysis;

import com.challenge.analysis.util.HTMLAnalysisConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.challenge.analysis")
public class HTMLAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(HTMLAnalysisApplication.class, args);
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo());
    }


    private ApiInfo generateApiInfo() {
        return new ApiInfo("Crawler API", "Crawler API to crawl website content.", "1.0",
                "Terms Of Services",
                new Contact(HTMLAnalysisConstants.ME, "", HTMLAnalysisConstants.MY_EMAIL_ID), "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}
