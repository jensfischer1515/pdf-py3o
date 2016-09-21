package com.epages.pdf;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix="pdf")
public class PdfRenderProperties {
    private URI renderApi;
    private Resource template;
    private Resource logo;
}
