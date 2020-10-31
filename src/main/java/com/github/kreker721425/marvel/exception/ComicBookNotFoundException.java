package com.github.kreker721425.marvel.exception;

public class ComicBookNotFoundException extends RuntimeException {

    public ComicBookNotFoundException() {
        super();
    }

    public ComicBookNotFoundException(String message) {
        super(message);
    }

    public ComicBookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComicBookNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ComicBookNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
