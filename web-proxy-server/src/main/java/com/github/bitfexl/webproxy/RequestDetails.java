package com.github.bitfexl.webproxy;

import com.sun.net.httpserver.HttpExchange;
import lombok.Value;

/**
 * Holds details about the original request.
 */
@Value
public class RequestDetails {
    /**
     * The scheme used.
     */
    String scheme;
    /**
     * The hostname without port.
     */
    String hostName;
    /**
     * The port, must always be set.
     */
    int port;
    /**
     * The request path (without uri parameters).
     */
    String path;
    /**
     * The request exchange.
     * Should only be used if not possible otherwise, as the RequestExtractor should handle the correct parsing of a request.
     */
    HttpExchange exchange;
}
