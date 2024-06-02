package com.github.bitfexl.webproxy;

import com.sun.net.httpserver.HttpExchange;

public interface RequestExtractor {
    RequestDetails extract(HttpExchange exchange) throws MalformedRequestException;
}
