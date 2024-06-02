package com.github.bitfexl.webproxy.processing;

import lombok.Data;

@Data
public class Response extends HTTPMessage {
    private int responseCode;
}
