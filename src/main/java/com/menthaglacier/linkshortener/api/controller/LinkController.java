package com.menthaglacier.linkshortener.api.controller;

import com.menthaglacier.linkshortener.api.ApiPaths;
import com.menthaglacier.linkshortener.api.model.link.LinkResponse;
import com.menthaglacier.linkshortener.api.model.link.LinkCreateRequest;
import com.menthaglacier.linkshortener.api.mapper.LinkMapper;
import com.menthaglacier.linkshortener.core.service.LinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.BASE_PATH + ApiPaths.API_VERSION + ApiPaths.LINKS)
public class LinkController {
    private final LinkService linkService;
    private final LinkMapper linkMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LinkResponse create(@Valid @RequestBody LinkCreateRequest linkCreateRequest) {
        return linkMapper.toResponse(linkService.create(linkCreateRequest.originalUrl()));
    }

    @DeleteMapping("/{shortCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String shortCode) {
        linkService.delete(shortCode);
    }
}
