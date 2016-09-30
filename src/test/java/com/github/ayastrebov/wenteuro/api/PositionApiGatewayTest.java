package com.github.ayastrebov.wenteuro.api;

import com.github.ayastrebov.wenteuro.api.model.Position;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class PositionApiGatewayTest {

    private final PositionApiGateway unit = new PositionApiGateway();

    @Test
    public void shouldGetSuggestions() {
        List<Position> suggestions = unit.getSuggestions("whatever");

        assertThat(suggestions, hasSize(2));
    }
}
