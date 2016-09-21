package com.epages.pdf;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
@Profile("!test")
@RequiredArgsConstructor
public class PdfRenderer implements CommandLineRunner {

    @NonNull
    private final Py3oFusionClient client;

    @NonNull
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        try (InputStream pdf = client.render(invoice(), imageMapping())) {
            copy(pdf, System.out);
        }
    }

    private ImageMapping imageMapping() {
        ImageMapping imageMapping = ImageMapping.builder().mapping("logo", "staticimage.logo").build();
        log.info("{}", imageMapping);
        return imageMapping;
    }

    private Invoice invoice() throws IOException {
        Invoice invoice = objectMapper.readValue(new BufferedReader(new InputStreamReader(System.in)), Invoice.class);
        log.info("{}", invoice);
        return invoice;
    }

    // http://stackoverflow.com/a/39070240/1393467
    private void copy(final InputStream in, final OutputStream out) throws IOException {
        final byte[] b = new byte[8192];
        for (int r; (r = in.read(b)) != -1; ) {
            out.write(b, 0, r);
        }
    }
}
