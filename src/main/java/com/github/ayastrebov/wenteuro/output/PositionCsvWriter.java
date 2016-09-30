package com.github.ayastrebov.wenteuro.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.ayastrebov.wenteuro.model.Position;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;

public class PositionCsvWriter {

    private abstract class PositionMixIn {

        @JsonProperty("_id")
        String id;

    }

    private final ObjectWriter writer;
    private final OutputStream out;

    public PositionCsvWriter(OutputStream out) {
        this.out = out;

        CsvMapper mapper = new CsvMapper();

        mapper.addMixIn(Position.class, PositionMixIn.class);
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

        CsvSchema schema = CsvSchema.builder()
            .addColumns(Arrays.asList("_id", "name", "type", "latitude", "longitude"), CsvSchema.ColumnType.STRING)
            .build()
            .withHeader();

        this.writer = mapper.writerFor(Position.class).with(schema);
    }

    public void write(List<Position> positions) {
        try {
            writer.writeValues(out)
                .writeAll(positions)
                .close();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
