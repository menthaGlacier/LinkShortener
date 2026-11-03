package com.menthaglacier.linkshortener.api.model.link;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record LinkCreateRequest(
        @NotBlank
        @Size(max = 2048)
        @URL
        String originalUrl
) {
}
