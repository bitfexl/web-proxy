package com.github.bitfexl.webproxy;

import com.github.bitfexl.webproxy.processing.Body;
import com.github.bitfexl.webproxy.processing.BodyParser;
import com.github.bitfexl.webproxy.processing.HTTPMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class BodyParserList implements BodyParser {
    private final Set<BodyParser<?>> parsers = new HashSet<>();

    private Iterable<BodyParser<?>> petrifiedParsers = List.of();

    public void add(BodyParser<?> parser) {
        synchronized (parsers) {
            boolean updated = parsers.add(parser);
            if (updated) {
                petrifiedParsers = List.copyOf(parsers);
            }
        }
    }

    public boolean remove(BodyParser<?> parser) {
        synchronized (parsers) {
            boolean updated = parsers.remove(parser);
            if (updated) {
                petrifiedParsers = List.copyOf(parsers);
            }
            return updated;
        }
    }

    @Override
    public Body tryParse(HTTPMessage message, byte[] body) {
        for (BodyParser<?> parser : petrifiedParsers) {
            final Body parsedBody = parser.tryParse(message, body);
            if (parsedBody != null) {
                return parsedBody;
            }
        }
        return null;
    }
}
