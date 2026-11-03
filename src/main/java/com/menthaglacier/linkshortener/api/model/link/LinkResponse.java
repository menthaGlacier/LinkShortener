package com.menthaglacier.linkshortener.api.model.link;

import lombok.Builder;

@Builder
public record LinkResponse(
        String shortCode,
        String originalUrl,
        String shortenedLink
) {
}
