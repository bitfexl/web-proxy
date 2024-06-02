package com.github.bitfexl.webproxy.processing;

import java.nio.charset.StandardCharsets;

public interface Body<T> {
    /**
     * Get the parsed body type, may be of any type, but common types should be documented here.
     * @return The type which will be returned by getParsed().
     */
    Class<T> getParsedType();

    /**
     * Get the parsed body. Should be modifiable, or a setter should be provided.
     * @return The parsed body.
     */
    T getParsed();

    /**
     * Get the raw body in bytes. Must always be implemented.
     * @return The raw body.
     */
    byte[] getRaw();

    /**
     * Set the raw body. Can cause an error if the bytes cant be parsed to the right type.
     * @param newBody The new raw body.
     */
    void setRaw(byte[] newBody);

    /**
     * Get the body as a string. Can throw an error. As this is a common operation, it should be cached if possible.
     * @return The body as a string.
     */
    default String getString() {
        return new String(getRaw(), StandardCharsets.UTF_8);
    }

    /**
     * Set the body as a string. The default implementation uses setRaw().
     * @param s The raw body as a string.
     */
    default void setString(String s) {
        setRaw(s.getBytes(StandardCharsets.UTF_8));
    }
}
