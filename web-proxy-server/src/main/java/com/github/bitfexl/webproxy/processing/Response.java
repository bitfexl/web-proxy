package com.github.bitfexl.webproxy.processing;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Response extends HTTPMessage {
    private int responseCode;
}
