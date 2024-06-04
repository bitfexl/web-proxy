package com.github.bitfexl.webproxy;

import com.github.bitfexl.webproxy.processing.BodyParser;
import com.github.bitfexl.webproxy.processing.Request;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface RequestExtractor {
    Request extract(HttpExchange exchange, BodyParser<?> bodyParser) throws MalformedRequestException, IOException;
}
