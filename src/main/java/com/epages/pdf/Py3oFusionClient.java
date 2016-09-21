package com.epages.pdf;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.apache.http.entity.ContentType.DEFAULT_BINARY;
import static org.apache.http.entity.ContentType.TEXT_PLAIN;
import static org.apache.http.entity.mime.HttpMultipartMode.BROWSER_COMPATIBLE;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class Py3oFusionClient {

    @NonNull
    private final HttpClient httpClient;

    @NonNull
    private final PdfRenderProperties properties;

    @NonNull
    private final ObjectMapper objectMapper;

    public InputStream render(Invoice invoice, ImageMapping imageMapping) throws IOException {
        log.info("Using '{}' for PDF rendering", properties.getRenderApi());
        HttpPost post = new HttpPost(properties.getRenderApi());
        try (InputStream template = properties.getTemplate().getInputStream(); InputStream logo = properties.getLogo().getInputStream()) {
            HttpEntity entity = MultipartEntityBuilder.create() //
                    .setMode(BROWSER_COMPATIBLE) //
                    .addTextBody("targetformat", "pdf", TEXT_PLAIN) //
                    .addBinaryBody("tmpl_file", template, DEFAULT_BINARY, "template") //
                    .addBinaryBody("logo", logo, DEFAULT_BINARY, "logo") //
                    .addTextBody("image_mapping", toJson(imageMapping), APPLICATION_JSON) //
                    .addTextBody("datadict", toJson(invoice), APPLICATION_JSON) //
                    .build();

            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            return response.getEntity().getContent();
        } finally {
            post.completed();
        }
    }

    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
