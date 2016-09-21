package com.epages.pdf;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class PdfRunnerApplicationTests {

    @Autowired
    private PdfRenderProperties properties;

    @Test
    public void contextLoads() {
        log.warn("Configuration: {}", properties);
    }
}
