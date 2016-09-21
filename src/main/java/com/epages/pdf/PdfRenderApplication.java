package com.epages.pdf;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import java.util.Locale;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
@EnableConfigurationProperties(PdfRenderProperties.class)
public class PdfRenderApplication implements Jackson2ObjectMapperBuilderCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(PdfRenderApplication.class, args).close();
    }

    @Bean(destroyMethod = "close")
    public CloseableHttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder //
                .locale(Locale.ENGLISH) //
                .timeZone("UTC") //
                .indentOutput(true) //
                .serializationInclusion(ALWAYS) //
                .featuresToDisable(WRITE_DATES_AS_TIMESTAMPS, FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
