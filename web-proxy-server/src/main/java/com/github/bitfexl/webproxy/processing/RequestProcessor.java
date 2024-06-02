package com.github.bitfexl.webproxy.processing;

public interface RequestProcessor {
    Response process(Request request, RequestContext context);
}
