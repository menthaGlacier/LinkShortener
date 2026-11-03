package com.menthaglacier.linkshortener.core.exception;

public class LinkNotFoundException extends RuntimeException {
    public LinkNotFoundException() {
        super("Link not found");
    }
}
