package com.github.ayastrebov.wenteuro.api;

import com.github.ayastrebov.wenteuro.api.model.Position;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PositionApiGateway {

    public List<Position> getSuggestions(String text) {
        return Arrays.asList(
            new Position("376217", "Berlin", "location", new Position.Geo(new BigDecimal("52.52437"), new BigDecimal("13.41053"))),
            new Position("448103", "Berlingo", "location", new Position.Geo(new BigDecimal("45.50298"), new BigDecimal("10.04366")))
        );
    }
}
