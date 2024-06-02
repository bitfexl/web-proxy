package com.github.bitfexl.webproxy;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebProxy implements HttpHandler {
    private final HTTPServerProxy httpServer;

    private final RequestExtractor extractor;

    private final RequestForwarder forwarder;

    public WebProxy(int port) throws IOException {
        this.httpServer = new HTTPServerProxy(new InetSocketAddress(port), 50, this);
        this.extractor = new RequestExtractorImpl();
        this.forwarder = new RequestForwarder();
        httpServer.start();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        final RequestDetails details = extractor.extract(exchange);
//        forwarder.complete(details);
    }

    // Hooks:
    // before request (original headers/body)
    // after request (response headers/body)
}
