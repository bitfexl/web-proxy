package com.github.bitfexl.webproxy.processing;

public interface BodyParser<T> {
    /**
     * Try to parse the body of a http message.
     * @param message The http message. May be of type Request, Response or any other, everything except the body can be expected to be set. Should only be modified if absolutely necessary.
     * @param body The raw body to try to parse.
     * @return The parsed body or null if the body cannot be parsed.
     */
    Body<T> tryParse(HTTPMessage message, byte[] body);
}
