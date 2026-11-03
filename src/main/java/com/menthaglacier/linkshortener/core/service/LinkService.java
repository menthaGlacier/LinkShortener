package com.menthaglacier.linkshortener.core.service;

import com.menthaglacier.linkshortener.core.entity.LinkEntity;
import com.menthaglacier.linkshortener.core.exception.LinkNotFoundException;
import com.menthaglacier.linkshortener.core.repository.LinkRepository;
import com.menthaglacier.linkshortener.core.utility.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LinkService {
    private static final int MAX_GENERATION_ATTEMPTS = 10;

    private final LinkRepository linkRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    @Transactional
    public LinkEntity create(String originalUrl) {
        LinkEntity link = new LinkEntity(generateUniqueCode(), originalUrl, Instant.now());
        return linkRepository.save(link);
    }

    @Transactional(readOnly = true)
    public String resolve(String shortCode) {
        LinkEntity link = linkRepository.findByShortCode(shortCode)
                .orElseThrow(LinkNotFoundException::new);

        if (!link.isActive()) {
            throw new LinkNotFoundException();
        }

        return link.getOriginalUrl();
    }

    private String generateUniqueCode() {
        for (int i = 0; i < MAX_GENERATION_ATTEMPTS; i++) {
            String shortCode = shortCodeGenerator.generate();
            if (!linkRepository.existsByShortCode(shortCode)) {
                return shortCode;
            }
        }

        throw new IllegalStateException("Could not generate a unique short code for a link");
    }
}
