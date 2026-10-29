package com.menthaglacier.linkshortener;

import org.springframework.boot.SpringApplication;

public class TestLinkShortenerApplication {
    public static void main(String[] args) {
        SpringApplication.from(LinkShortenerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
}
