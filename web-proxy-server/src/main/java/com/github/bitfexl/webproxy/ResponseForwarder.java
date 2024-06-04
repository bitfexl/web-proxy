package com.github.bitfexl.webproxy;

import com.github.bitfexl.webproxy.processing.Response;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class ResponseForwarder {
    public void complete(HttpExchange exchange, Response response) throws IOException {
        for (String header : response.getHeaders().keySet()) {
            for (String value : response.getHeaderValues(header)) {
                exchange.getResponseHeaders().add(header, value);
            }
        }

        final byte[] rawBody = response.getBody().getRaw();

        exchange.sendResponseHeaders(response.getResponseCode(), rawBody.length);

        exchange.getResponseBody().write(rawBody);
    }
}
