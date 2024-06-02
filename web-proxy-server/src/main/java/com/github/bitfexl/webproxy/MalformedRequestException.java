package com.github.bitfexl.webproxy;

public class MalformedRequestException extends RuntimeException {
    public MalformedRequestException() {
    }

    public MalformedRequestException(String message) {
        super(message);
    }

    public MalformedRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedRequestException(Throwable cause) {
        super(cause);
    }
}
