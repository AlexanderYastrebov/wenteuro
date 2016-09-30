package com.github.ayastrebov.wenteuro;

import com.github.ayastrebov.wenteuro.api.PositionApi;
import com.github.ayastrebov.wenteuro.model.Position;
import com.github.ayastrebov.wenteuro.output.PositionCsvWriter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProcessorTest {

    private PositionApi api;
    private PositionCsvWriter writer;

    private Processor unit;

    @Before
    public void setUp() {
        api = mock(PositionApi.class);
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
