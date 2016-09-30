package com.github.ayastrebov.wenteuro.output;

import com.github.ayastrebov.wenteuro.model.Position;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PositionCsvWriterTest {

    private ByteArrayOutputStream buffer;
    private PositionCsvWriter unit;

    @Before
    public void setUp() {
        buffer = new ByteArrayOutputStream();
        unit = new PositionCsvWriter(buffer);
    }

    @Test
    public void shouldWritePositions() throws IOException {
        List<Position> positions = Arrays.asList(
            new Position("376217", "Berlin", "location", new BigDecimal("52.52437"), new BigDecimal("13.41053")),
            new Position("448103", "Berlingo", "location", new BigDecimal("45.50298"), new BigDecimal("10.04366"))
        );

        unit.write(positions);

        assertLines("Berlin.csv");
    }

    private void assertLines(String name) throws IOException {
        try (
            InputStream expected = PositionCsvWriterTest.class.getResourceAsStream("/data/" + name);
            InputStream result = new ByteArrayInputStream(buffer.toByteArray())) {

            List<String> expectedLines = getLines(expected);
            List<String> resultLines = getLines(result);

            assertEquals(expectedLines.size(), resultLines.size());
            for (int i = 0; i < expectedLines.size(); i++) {
                assertThat("Line " + (i + 1) + " does not match", resultLines.get(i), is(expectedLines.get(i)));
            }
        }
    }

    private List<String> getLines(InputStream is) {
        return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.toList());
    }
}
