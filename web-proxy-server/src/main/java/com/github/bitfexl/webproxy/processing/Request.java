package com.github.bitfexl.webproxy.processing;

import com.github.bitfexl.webproxy.RequestDetails;
import lombok.Data;

@Data
public class Request extends HTTPMessage {
    private final RequestDetails requestDetails;

    private String method;
}
