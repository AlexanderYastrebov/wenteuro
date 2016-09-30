package com.github.ayastrebov.wenteuro.api;

import com.github.ayastrebov.wenteuro.model.Position;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class PositionApiGatewayTest {

    private final PositionApiGateway unit = new PositionApiGateway();

    @Test
    public void shouldGetSuggestions() {
        List<Position> suggestions = unit.getSuggestions("whatever");

        assertThat(suggestions, hasSize(2));

        Position p1 = suggestions.get(0);

        assertThat(p1.getId(), is("376217"));
        assertThat(p1.getName(), is("Berlin"));
        assertThat(p1.getType(), is("location"));
        assertThat(p1.getLatitude(), is(new BigDecimal("52.52437")));
        assertThat(p1.getLongitude(), is(new BigDecimal("13.41053")));
    }
}
