package com.github.bitfexl.webproxy;

import com.github.bitfexl.webproxy.processing.BodyParser;
import com.github.bitfexl.webproxy.processing.ProcessorChain;
import com.github.bitfexl.webproxy.processing.Request;
import com.github.bitfexl.webproxy.processing.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebProxy implements HttpHandler {
    private final HTTPServerProxy httpServer;

    private final RequestExtractor extractor;

    private final ProcessorChain processorChain;

    private final BodyParserList bodyParserList;

    private final ResponseForwarder responseForwarder;

    public WebProxy(int port) throws IOException {
        this.httpServer = new HTTPServerProxy(new InetSocketAddress(port), 50, this);
        this.extractor = new RequestExtractorImpl();
        this.processorChain = new ProcessorChain();
        this.bodyParserList = new BodyParserList();
        this.responseForwarder = new ResponseForwarder();
        httpServer.start();
    }

    // todo: request forwarder somewhere here or in chain

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        final Request request = extractor.extract(exchange, bodyParserList);
        final Response response = processorChain.processRequest(request);
        responseForwarder.complete(exchange, response);
    }

    // Hooks:
    // before request (original headers/body)
    // after request (response headers/body)
}
