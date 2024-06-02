package com.github.bitfexl.webproxy.processing;

import java.util.ArrayList;
import java.util.List;

public class ProcessorChain {
    private final List<RequestProcessor> processors = new ArrayList<>();

    // the processors in the correct call order, unmodifiable
    private List<RequestProcessor> petrifiedProcessors = List.of();

    public Response processRequest(Request request) {
        final RequestContext context = new RequestContext(petrifiedProcessors.iterator());
        if (!context.hasNext()) {
            throw new IllegalStateException("No processor was set.");
        }
        return context.next(request);
    }

    public void addProcessor(RequestProcessor processor) {
        synchronized (processors) {
            processors.add(processor);
            petrifiedProcessors = List.copyOf(processors.reversed());
        }
    }
}
