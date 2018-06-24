package com.challenge.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
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
}
