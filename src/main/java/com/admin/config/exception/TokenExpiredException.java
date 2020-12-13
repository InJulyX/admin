package com.admin.config.exception;

public class TokenExpiredException extends RuntimeException {
    private static final long serialVersionUID = -2L;

    public TokenExpiredException() {
    }

    public TokenExpiredException(String message) {
        super(message);
    }

}
