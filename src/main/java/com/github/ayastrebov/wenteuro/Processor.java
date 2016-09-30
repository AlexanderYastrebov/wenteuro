package com.github.ayastrebov.wenteuro;

import com.github.ayastrebov.wenteuro.output.PositionCsvWriter;
import com.github.ayastrebov.wenteuro.api.PositionApi;

public class Processor {

    private final PositionApi api;
    private final PositionCsvWriter writer;

    public Processor(PositionApi api, PositionCsvWriter writer) {
        this.api = api;
        this.writer = writer;
    }

    public void process(String text) {
        writer.write(api.getSuggestions(text));
    }
}
