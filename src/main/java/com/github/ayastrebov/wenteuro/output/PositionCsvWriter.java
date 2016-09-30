package com.github.ayastrebov.wenteuro.output;

import com.github.ayastrebov.wenteuro.model.Position;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PositionCsvWriter {

    private final OutputStream out;

    public PositionCsvWriter(OutputStream out) {
        this.out = out;
    }

    public void write(List<Position> positions) {
        try {
            out.write((""
                + "_id,name,type,latitude,longitude\n"
                + "376217,Berlin,location,52.52437,13.41053,\n"
                + "448103,Berlingo,location,45.50298,10.04366\n"
                + "").getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }

    }
}
