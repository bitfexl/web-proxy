package com.github.bitfexl.webproxy.processing;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class HTTPMessage {
    private final Map<String, List<String>> headers = new HashMap<>();

    @Getter
    @Setter
    private Body<?> body;

    /**
     * Add a header value.
     * @param key The header name.
     * @param value The value. If a header with the given name is already present a second header is added.
     */
    public void addHeader(String key, String value) {
        getHeaderValues(key).add(value);
    }

    /**
     * Get all values set for a given header.
     * @param key The header name.
     * @return A list or an empty list with the headers value(s) (if specified multiple times) can be modified to add or remove the given header with the given value.
     */
    public List<String> getHeaderValues(String key) {
        return headers.computeIfAbsent(key, s -> new LinkedList<>());
    }

    /**
     * Check if a given header is set at least once.
     * @param key The header name.
     * @return true: header is set at least once, false: header not set;
     */
    public boolean isHeaderSet(String key) {
        final List<String> values = headers.get(key);
        if (values == null) {
            return false;
        }
        return !values.isEmpty();
    }
}
