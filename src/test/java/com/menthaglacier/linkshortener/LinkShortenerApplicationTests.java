package com.menthaglacier.linkshortener;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class LinkShortenerApplicationTests {
    @Test
    void contextLoads() {
    }
}
