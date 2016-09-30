package com.github.ayastrebov.wenteuro.api;

import com.github.ayastrebov.wenteuro.Processor;
import com.github.ayastrebov.wenteuro.model.Position;
import com.github.ayastrebov.wenteuro.output.PositionCsvWriter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProcessorTest {

    private PositionApiGateway api;
    private PositionCsvWriter writer;

    private Processor unit;

    @Before
    public void setUp() {
        api = mock(PositionApiGateway.class);
        writer = mock(PositionCsvWriter.class);

        unit = new Processor(api, writer);
    }

    @Test
    public void shouldProcess() {
        unit.process("whatever");

        verify(api).getSuggestions("whatever");
        verify(writer).write(anyListOf(Position.class));
    }
}
