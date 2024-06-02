package com.github.bitfexl.webproxy.processing;

import java.util.Iterator;

public class RequestContext {
    private final Iterator<RequestProcessor> processors;

    public RequestContext(Iterator<RequestProcessor> processors) {
        this.processors = processors;
    }

    public boolean hasNext() {
        return processors.hasNext();
    }

    public Response next(Request request) {
        if (!processors.hasNext()) {
            throw new IllegalStateException("No more processors.");
        }

        return processors.next().process(request, this);
    }
}
