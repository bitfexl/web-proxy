package com.github.bitfexl.webproxy;

import com.sun.net.httpserver.HttpExchange;
import lombok.Value;

@Value
public class RequestDetails {
    String scheme;
    String hostName;
    int port;
    String path;
    HttpExchange exchange;
}
