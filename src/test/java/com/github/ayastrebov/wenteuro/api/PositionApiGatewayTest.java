package com.github.ayastrebov.wenteuro.api;

import com.github.ayastrebov.wenteuro.model.Position;
import feign.Client;
import feign.Request;
import feign.Response;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PositionApiGatewayTest {

    private Client client;
    private PositionApiGateway unit;

    @Before
    public void setUp() {
        client = mock(Client.class);

        unit = PositionApiGatewayFactory.create(client);
    }

    @Test
    public void shouldGetSuggestions() throws IOException {
        when(client.execute(any(Request.class), any(Request.Options.class)))
            .thenReturn(Response.create(200, "OK", Collections.<String, Collection<String>>emptyMap(), resource("Berlin.json"), null));

        List<Position> suggestions = unit.getSuggestions("whatever");

        assertThat(suggestions, hasSize(8));

        Position p1 = suggestions.get(0);

        assertThat(p1.getId(), is("376217"));
        assertThat(p1.getName(), is("Berlin"));
        assertThat(p1.getType(), is("location"));
        assertThat(p1.getLatitude(), is(new BigDecimal("52.52437")));
        assertThat(p1.getLongitude(), is(new BigDecimal("13.41053")));
    }

    private InputStream resource(String name) {
        return PositionApiGatewayTest.class.getResourceAsStream("/data/" + name);
    }
}
