package com.github.bitfexl.webproxy;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.java.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@Log
public class HTTPServerProxy {
    private final HttpServer httpServer;

    public HTTPServerProxy(InetSocketAddress address, int backlog, HttpHandler handler) throws IOException {
        httpServer = HttpServer.create(address, backlog);
        httpServer.setExecutor(Executors.newVirtualThreadPerTaskExecutor());

        final HttpContext httpContext = httpServer.createContext("/");
        httpContext.setHandler(exchange -> {
            try (exchange) {
                handler.handle(exchange);
            }
        }); // todo: make sure request is handled properly
    }

    public void start() {
        httpServer.start();
    }

    public void stop() {
        httpServer.stop(0);
    }
}
