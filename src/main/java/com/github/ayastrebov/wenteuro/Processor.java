package com.github.ayastrebov.wenteuro;

import com.github.ayastrebov.wenteuro.api.PositionApiGateway;
import com.github.ayastrebov.wenteuro.output.PositionCsvWriter;

public class Processor {

    private final PositionApiGateway api;
    private final PositionCsvWriter writer;

    public Processor(PositionApiGateway api, PositionCsvWriter writer) {
        this.api = api;
        this.writer = writer;
    }

    public void process(String text) {
        writer.write(api.getSuggestions(text));
    }
}
