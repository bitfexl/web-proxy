package com.github.bitfexl.webproxy.processing;

import com.github.bitfexl.webproxy.RequestDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Request extends HTTPMessage {
    private final RequestDetails requestDetails;

    private String method;
}
