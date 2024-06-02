package com.github.bitfexl.webproxy;

import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.util.regex.Pattern;

public class RequestExtractorImpl implements RequestExtractor {
    // The url should be
    // https://proxy.example.com/SESSION_ID_MAYBE/SCHEME/HOST_NAME/PORT/PATH
    // https://proxy.example.com/proxy_1234/https/example.com/80/index.html

    @Override
    public RequestDetails extract(HttpExchange exchange) {
        final URI uri = exchange.getRequestURI();
        if (uri.getRawPath() == null) {
            throw new NullPointerException("URI path not present.");
        }

        final String rawPath =  uri.getRawPath();
        final String[] pathParts = rawPath.split(Pattern.quote("/"), 6);
        if (pathParts.length < 5) {
            throw new MalformedRequestException("URI path not long enough.");
        }

        final String sessionId = pathParts[1];
        final String scheme = pathParts[2];
        final String hostName = pathParts[3];
        final int port;
        try {
            port = Integer.parseInt(pathParts[4]);
            if (port < 0 || port > 65535) {
                throw new MalformedRequestException("Target port out of range.");
            }
        } catch (NumberFormatException ex) {
            throw new MalformedRequestException("Target port must be a number.");
        }
        final String path = "/" + (pathParts.length == 6 ? pathParts[5] : rawPath.endsWith("/") ? "/" : "");

        return new RequestDetails(
                scheme,
                hostName,
                port,
                path,
                exchange
        );
    }
}
