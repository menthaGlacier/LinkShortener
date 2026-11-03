package com.menthaglacier.linkshortener.api.mapper;

import com.menthaglacier.linkshortener.api.model.link.LinkResponse;
import com.menthaglacier.linkshortener.core.entity.LinkEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LinkMapper {
    private final String baseUrl;

    public LinkMapper(@Value("${app.link.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public LinkResponse toResponse(LinkEntity link) {
        return LinkResponse.builder()
                .originalUrl(link.getOriginalUrl())
                .shortCode(link.getShortCode())
                .shortenedLink(baseUrl + "/" + link.getShortCode())
                .build();
    }
}
